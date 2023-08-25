package coffeehouse.validation;

import coffeehouse.model.EventTypes;
import coffeehouse.entity.OrderEvent;
import coffeehouse.repo.OrderEventRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderEventValidationService {

    public boolean validate(List<OrderEvent> events) {
        boolean confirmRegistration = false;
        boolean confirmEnded = false;
        for (OrderEvent event : events) {
            if (event.getEventType() == EventTypes.ACCEPTED || event.getEventType() == EventTypes.CANCELED) {
                confirmEnded = true;
            }
            if (event.getEventType() == EventTypes.REGISTERED) {
                confirmRegistration = true;
            }
        }
        if (!confirmEnded && confirmRegistration) {
            return true;
        } else {
            return false;
        }
    }
}
