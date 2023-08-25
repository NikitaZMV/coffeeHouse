package coffeehouse.entity;

import coffeehouse.model.EventTypes;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "events")
public class OrderEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "order_id", nullable = false)
    int orderId;   //Идентификатор заказа

    @Column(name = "employee_id", nullable = false)
    int employeeId;  //Идентификтор сотрудника

    @Column(name = "datetime", nullable = false)
    Date eventDateTime;  //Дата и время

    @Column(name = "event_type", nullable = false)
    EventTypes eventType;
}
