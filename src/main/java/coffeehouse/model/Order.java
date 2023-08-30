package coffeehouse.model;

import coffeehouse.entity.OrderEvent;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    private OrderStatus status;
    private int orderId;
    private int employeeId;
    private int productId;
    private int clientId;
    private BigDecimal productPrice;
    private String cancellationReason = "not cancelled";
    private Date creationTime;
    private List<OrderEvent> events = new ArrayList<>();
}
