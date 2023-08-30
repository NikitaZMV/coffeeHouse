package coffeehouse.service;

import coffeehouse.Dto.CoffeeHouseDto;
import coffeehouse.entity.*;
import coffeehouse.exception.CoffeeHouseException;
import coffeehouse.model.Order;
import coffeehouse.model.OrderStatus;
import coffeehouse.repo.*;
import coffeehouse.validation.OrderEventValidationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    final Map<Class<? extends OrderEvent>, OrderStatus> statusMap = new HashMap<>() {{
        this.put(OrderRegisteredEvent.class, OrderStatus.REGISTERED);
        this.put(OrderAcceptedEvent.class, OrderStatus.ACCEPTED);
        this.put(OrderReadyEvent.class, OrderStatus.READY);
        this.put(OrderIssuedEvent.class, OrderStatus.ISSUED);
        this.put(OrderCancelledEvent.class, OrderStatus.CANCELED);
    }};
    private final OrderRegisteredEventRepo registeredEventRepo;
    private final OrderCanceledEventRepo orderCanceledEventRepo;
    private final OrderAcceptedEventRepo orderAcceptedEventRepo;
    private final OrderReadyEventRepo orderReadyEventRepo;
    private final OrderIssuedEventRepo orderIssuedEventRepo;
    private final OrderEventRepo<OrderEvent> origRepo;
    private final OrderEventValidationService validator;

    public OrderServiceImpl(OrderRegisteredEventRepo registeredEventRepo, OrderCanceledEventRepo orderCanceledEventRepo, OrderAcceptedEventRepo orderAcceptedEventRepo, OrderReadyEventRepo orderReadyEventRepo, OrderIssuedEventRepo orderIssuedEventRepo, OrderEventRepo<OrderEvent> origRepo, OrderEventValidationService validator) {
        this.registeredEventRepo = registeredEventRepo;
        this.orderCanceledEventRepo = orderCanceledEventRepo;
        this.orderAcceptedEventRepo = orderAcceptedEventRepo;
        this.orderReadyEventRepo = orderReadyEventRepo;
        this.orderIssuedEventRepo = orderIssuedEventRepo;
        this.origRepo = origRepo;
        this.validator = validator;
    }

    @Override
    public CoffeeHouseDto<OrderEvent> publishEvent(OrderEvent event) throws CoffeeHouseException {
        List<OrderEvent> events = new ArrayList<>(origRepo.findByOrderId(event.getOrderId()));
        validator.validate(events, event);
        switch (event) {
            case OrderRegisteredEvent registeredEvent -> registeredEventRepo.save(registeredEvent);
            case OrderAcceptedEvent acceptedEvent -> orderAcceptedEventRepo.save(acceptedEvent);
            case OrderReadyEvent readyEvent -> orderReadyEventRepo.save(readyEvent);
            case OrderIssuedEvent issuedEvent -> orderIssuedEventRepo.save(issuedEvent);
            case OrderCancelledEvent canceledEvent -> orderCanceledEventRepo.save(canceledEvent);

            default -> throw new IllegalStateException("Unexpected value: " + event);
        }
        return new CoffeeHouseDto(event);
    }

    @Override
    public CoffeeHouseDto<Order> findOrder(int orderId) {
        Order order = new Order();
        List<OrderEvent> events = new ArrayList<>(origRepo.findByOrderIdOrderByEventDateTime(orderId));
        order.setStatus(statusMap.get(events.get(events.size() - 1).getClass()));
        OrderRegisteredEvent registeredEvent = (OrderRegisteredEvent) events.get(0);
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
        return new CoffeeHouseDto<>(order);
    }
}

