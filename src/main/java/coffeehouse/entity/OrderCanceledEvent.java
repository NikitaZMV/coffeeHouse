package coffeehouse.entity;

import jakarta.persistence.Column;
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
@DiscriminatorValue("CANCELLED")
public class OrderCanceledEvent extends OrderEvent {
    @Column(name = "cancellation_reason")
    String cancellationReason;

    public OrderCanceledEvent(int orderId, int employeeId, Date eventDateTime, String cancellationReason) {
        super(orderId, employeeId, eventDateTime);
        this.cancellationReason = cancellationReason;
    }
}
