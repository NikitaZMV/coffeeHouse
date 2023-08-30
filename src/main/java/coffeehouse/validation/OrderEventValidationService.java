package coffeehouse.validation;

import coffeehouse.entity.OrderAcceptedEvent;
import coffeehouse.entity.OrderCancelledEvent;
import coffeehouse.entity.OrderEvent;
import coffeehouse.entity.OrderRegisteredEvent;
import coffeehouse.exception.CoffeeHouseException;
import coffeehouse.exception.EventAlredyExistException;
import coffeehouse.exception.OrderCompletedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderEventValidationService {

    public void validate(List<OrderEvent> events, OrderEvent publishedEvent) throws CoffeeHouseException {
        if (!events.isEmpty() && !publishedEvent.getClass().equals(OrderRegisteredEvent.class)) {
            for (OrderEvent event : events) {
                if (event.getClass().equals(publishedEvent.getClass())) {
                    throw new EventAlredyExistException();
                } else if (event instanceof OrderAcceptedEvent || event instanceof OrderCancelledEvent) {
                    throw new OrderCompletedException();
                }
            }
        }
    }
}
