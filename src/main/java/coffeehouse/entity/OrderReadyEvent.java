package coffeehouse.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@DiscriminatorValue("READY")
@NoArgsConstructor
public class OrderReadyEvent extends OrderEvent {
    public OrderReadyEvent(int orderId, int employeeId, Date eventDateTime) {
        super(orderId, employeeId, eventDateTime);
    }
}