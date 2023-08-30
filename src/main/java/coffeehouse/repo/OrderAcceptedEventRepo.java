package coffeehouse.repo;

import coffeehouse.entity.OrderAcceptedEvent;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderAcceptedEventRepo extends OrderEventRepo<OrderAcceptedEvent>{
}
