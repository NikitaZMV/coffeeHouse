package coffeehouse.repo;

import coffeehouse.entity.OrderEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEventRepo extends CrudRepository<OrderEvent, Long> {
}
