package coffeehouse.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@DiscriminatorValue("ISSUED")
public class OrderIssuedEvent extends OrderEvent {
    public OrderIssuedEvent(int orderId, int employeeId, Date eventDateTime) {
        super(orderId, employeeId, eventDateTime);
    }
}

