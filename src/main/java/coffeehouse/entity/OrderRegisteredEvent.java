package coffeehouse.entity;

import coffeehouse.model.EventTypes;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "events")
public class OrderRegisteredEvent extends OrderEvent {

    @Column(name = "client_id")
    int clientId;   //Идентификатор клиента

    @Column(name = "expected_order_issue_time")
    Timestamp expectedOrderIssueTime; //Ожидаемое время выдачи

    @Column(name = "product_id")
    int productId;  //Идентфикатор товара

    @Column(name = "product_price")
    BigDecimal productPrice;  //Стоимость товара

    public OrderRegisteredEvent() {
        this.eventType = EventTypes.REGISTERED;
    }
}
