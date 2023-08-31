package CoffeeHouse.validation;

import coffeehouse.entity.*;
import coffeehouse.exception.CoffeeHouseException;
import coffeehouse.exception.EventAlredyExistException;
import coffeehouse.exception.OrderCompletedException;
import coffeehouse.validation.OrderEventValidationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class OrderEventValidationServiceTest {
    List<coffeehouse.entity.OrderEvent> events;
    OrderEvent publishedEvent;

    OrderEventValidationService validation = new OrderEventValidationService();

    @Test
    void validateWithExistedEvent() throws CoffeeHouseException {
        events = new ArrayList<>();
        events.add(new OrderRegisteredEvent());
        events.add(new OrderAcceptedEvent());
        events.add(new OrderReadyEvent());
        publishedEvent = new OrderReadyEvent();
        Assertions.assertThrows(EventAlredyExistException.class, () -> validation.validate(events, publishedEvent));
    }

    @Test
    void validateWithCompletedOrder() throws CoffeeHouseException {
        events = new ArrayList<>();
        events.add(new OrderRegisteredEvent());
        events.add(new OrderCancelledEvent());
        publishedEvent = new OrderReadyEvent();
        Assertions.assertThrows(OrderCompletedException.class, () -> validation.validate(events, publishedEvent));
    }


}
