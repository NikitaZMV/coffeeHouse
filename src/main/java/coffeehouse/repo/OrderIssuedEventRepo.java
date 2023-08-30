package coffeehouse.repo;

import coffeehouse.entity.OrderIssuedEvent;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderIssuedEventRepo extends OrderEventRepo<OrderIssuedEvent>{
}
