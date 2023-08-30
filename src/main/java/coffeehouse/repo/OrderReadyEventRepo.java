package coffeehouse.repo;

import coffeehouse.entity.OrderReadyEvent;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderReadyEventRepo extends OrderEventRepo<OrderReadyEvent>{
}
