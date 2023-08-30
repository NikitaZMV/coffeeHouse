package coffeehouse.repo;

import coffeehouse.entity.OrderAcceptedEvent;
import coffeehouse.entity.OrderEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderEventRepo<T extends OrderEvent> extends CrudRepository<T, Long> {
    List<OrderEvent> findByOrderId(int orderId);
    List<OrderEvent> findByOrderIdOrderByEventDateTime(int orderId); //OrderBy
}
