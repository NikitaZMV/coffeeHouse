package coffeehouse.model;

import coffeehouse.entity.OrderEvent;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    EventTypes currentEvent;
    List<OrderEvent> events = new ArrayList<>();
}
