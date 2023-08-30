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
@DiscriminatorValue("ACCEPTED")
public class OrderAcceptedEvent extends OrderEvent {

    public OrderAcceptedEvent(int orderId, int employeeId, Date eventDateTime) {
        super(orderId, employeeId, eventDateTime);
    }
}
