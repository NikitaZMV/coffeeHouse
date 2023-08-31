package coffeehouse.service;

import coffeehouse.dto.CoffeeHouseDto;
import coffeehouse.exception.CoffeeHouseException;
import coffeehouse.model.Order;
import coffeehouse.entity.OrderEvent;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    CoffeeHouseDto<? extends OrderEvent> publishEvent(OrderEvent event) throws CoffeeHouseException;
    CoffeeHouseDto<Order> findOrder(int id);
}