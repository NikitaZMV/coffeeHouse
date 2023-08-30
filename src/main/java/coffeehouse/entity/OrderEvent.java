package coffeehouse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "events")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,
        name = "event_type")
public abstract class OrderEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "order_id", nullable = false)
    int orderId;

    @Column(name = "employee_id", nullable = false)
    int employeeId;

    @Column(name = "datetime", nullable = false)
    Date eventDateTime;

    public OrderEvent(int orderId, int employeeId, Date eventDateTime) {
        this.orderId = orderId;
        this.employeeId = employeeId;
        this.eventDateTime = eventDateTime;
    }
}
