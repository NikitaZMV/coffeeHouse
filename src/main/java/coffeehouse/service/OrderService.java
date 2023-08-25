package coffeehouse.service;

import coffeehouse.model.Order;
import coffeehouse.entity.OrderEvent;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    void publishEvent(OrderEvent event);
    Order findOrder(int id);
}