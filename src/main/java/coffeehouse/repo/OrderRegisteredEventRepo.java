package coffeehouse.repo;

import coffeehouse.entity.OrderRegisteredEvent;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRegisteredEventRepo extends OrderEventRepo<OrderRegisteredEvent> {
}