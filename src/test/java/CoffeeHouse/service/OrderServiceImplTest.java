package CoffeeHouse.service;

import coffeehouse.Dto.CoffeeHouseDto;
import coffeehouse.entity.*;
import coffeehouse.exception.CoffeeHouseException;
import coffeehouse.model.Order;
import coffeehouse.model.OrderStatus;
import coffeehouse.repo.*;
import coffeehouse.service.OrderServiceImpl;
import coffeehouse.validation.OrderEventValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRegisteredEventRepo registeredEventRepo;
    @Mock
    private OrderCancelledEventRepo orderCancelledEventRepo;
    @Mock
    private OrderAcceptedEventRepo orderAcceptedEventRepo;
    @Mock
    private OrderReadyEventRepo orderReadyEventRepo;
    @Mock
    private OrderIssuedEventRepo orderIssuedEventRepo;
    @Mock
    private OrderEventRepo<OrderEvent> origRepo;
    @Mock
    private OrderEventValidationService validator;


    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void testPublishEvent_RegisteredEvent() throws CoffeeHouseException {
        OrderRegisteredEvent event = new OrderRegisteredEvent();
        when(registeredEventRepo.save(event)).thenReturn(new CoffeeHouseDto<OrderRegisteredEvent>().getPayload());

        CoffeeHouseDto<OrderEvent> result = orderService.publishEvent(event);

        verify(registeredEventRepo, times(1)).save(event);
        assertEquals(event, result.getPayload());
    }

    @Test
    public void testPublishEvent_AcceptedEvent() throws CoffeeHouseException {
        OrderAcceptedEvent event = new OrderAcceptedEvent();
        when(orderAcceptedEventRepo.save(event)).thenReturn(new CoffeeHouseDto<OrderAcceptedEvent>().getPayload());

        CoffeeHouseDto<OrderEvent> result = orderService.publishEvent(event);

        verify(orderAcceptedEventRepo, times(1)).save(event);
        assertEquals(event, result.getPayload());
    }

    @Test
    public void testPublishEvent_ReadyEvent() throws CoffeeHouseException {
        OrderReadyEvent event = new OrderReadyEvent();
        when(orderReadyEventRepo.save(event)).thenReturn(new CoffeeHouseDto<OrderReadyEvent>().getPayload());

        CoffeeHouseDto<OrderEvent> result = orderService.publishEvent(event);

        verify(orderReadyEventRepo, times(1)).save(event);
        assertEquals(event, result.getPayload());
    }

    @Test
    public void testPublishEvent_IssuedEvent() throws CoffeeHouseException {
        OrderIssuedEvent event = new OrderIssuedEvent();
        when(orderIssuedEventRepo.save(event)).thenReturn(new CoffeeHouseDto<OrderIssuedEvent>().getPayload());

        CoffeeHouseDto<OrderEvent> result = orderService.publishEvent(event);

        verify(orderIssuedEventRepo, times(1)).save(event);
        assertEquals(event, result.getPayload());
    }

    @Test
    public void testPublishEvent_CancelledEvent() throws CoffeeHouseException {
        OrderCancelledEvent event = new OrderCancelledEvent();
        when(orderCancelledEventRepo.save(event)).thenReturn(new CoffeeHouseDto<OrderCancelledEvent>().getPayload());

        CoffeeHouseDto<OrderEvent> result = orderService.publishEvent(event);

        verify(orderCancelledEventRepo, times(1)).save(event);
        assertEquals(event, result.getPayload());
    }

    @Test
    public void testFindOrder() {
        //int orderId = 1;
        OrderRegisteredEvent registeredEvent = new OrderRegisteredEvent();
        registeredEvent.setOrderId(1);
        registeredEvent.setClientId(123);
        registeredEvent.setProductId(132);
        registeredEvent.setEmployeeId(234);
        registeredEvent.setProductPrice(BigDecimal.valueOf(10.99));
        registeredEvent.setEventDateTime(new Date(10));

        OrderCancelledEvent cancelledEvent = new OrderCancelledEvent(
                registeredEvent.getOrderId(),
                registeredEvent.getEmployeeId(),
                registeredEvent.getEventDateTime(),
                "Reason"
        );

        Order order = new Order();

        List<OrderEvent> testEvents = new ArrayList<>();
        testEvents.add(registeredEvent);
        testEvents.add(cancelledEvent);

        when(origRepo.findByOrderIdOrderByEventDateTime(registeredEvent.getOrderId())).thenReturn(testEvents);
        List<OrderEvent> events = new ArrayList<>(origRepo.findByOrderIdOrderByEventDateTime(registeredEvent.getOrderId()));
        order.setStatus(OrderStatus.CANCELED);
        if (order.getStatus().equals(OrderStatus.CANCELED)) {
            OrderCancelledEvent canceledEvent = (OrderCancelledEvent) events.get(events.size() - 1);
            order.setCancellationReason(canceledEvent.getCancellationReason());
        }
        order.setEvents(events);
        order.setClientId(registeredEvent.getClientId());
        order.setProductId(registeredEvent.getProductId());
        order.setEmployeeId(registeredEvent.getEmployeeId());
        order.setCreationTime(registeredEvent.getEventDateTime());
        order.setProductPrice(registeredEvent.getProductPrice());

        CoffeeHouseDto<Order> result = new CoffeeHouseDto<>(order);

        assertEquals(OrderStatus.CANCELED, result.getPayload().getStatus());
        assertEquals(cancelledEvent.getCancellationReason(), result.getPayload().getCancellationReason());
        assertEquals(events, result.getPayload().getEvents());
        assertEquals(registeredEvent.getClientId(), result.getPayload().getClientId());
        assertEquals(registeredEvent.getProductId(), result.getPayload().getProductId());
        assertEquals(registeredEvent.getEmployeeId(), result.getPayload().getEmployeeId());
        assertEquals(registeredEvent.getEventDateTime(), result.getPayload().getCreationTime());
        assertEquals(registeredEvent.getProductPrice(), result.getPayload().getProductPrice());
    }
}


