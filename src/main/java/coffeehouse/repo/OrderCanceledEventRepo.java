package coffeehouse.repo;

import coffeehouse.entity.OrderCancelledEvent;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCanceledEventRepo extends OrderEventRepo<OrderCancelledEvent>{
}
