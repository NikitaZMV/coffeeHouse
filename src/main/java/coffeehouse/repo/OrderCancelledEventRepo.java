package coffeehouse.repo;

import coffeehouse.entity.OrderCancelledEvent;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCancelledEventRepo extends OrderEventRepo<OrderCancelledEvent>{
}
