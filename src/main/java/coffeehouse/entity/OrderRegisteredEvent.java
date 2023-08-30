package coffeehouse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@DiscriminatorValue("REGISTERED")
@NoArgsConstructor
public class OrderRegisteredEvent extends OrderEvent {

    @Column(name = "client_id")
    int clientId;

    @Column(name = "expected_order_issue_time")
    Date expectedOrderIssueTime;

    @Column(name = "product_id")
    int productId;

    @Column(name = "product_price")
    BigDecimal productPrice;

    public OrderRegisteredEvent(int orderId, int employeeId, Date eventDateTime, int clientId, Date expectedOrderIssueTime, int productId, BigDecimal productPrice) {
        super(orderId, employeeId, eventDateTime);
        this.clientId = clientId;
        this.expectedOrderIssueTime = expectedOrderIssueTime;
        this.productId = productId;
        this.productPrice = productPrice;
    }
}
