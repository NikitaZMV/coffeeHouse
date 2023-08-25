package coffeehouse.entity;

import coffeehouse.model.EventTypes;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "events")
public class OrderCanceledEvent extends OrderEvent {
    @Column(name = "cancellation_reason")
    String cancellationReason;

    public OrderCanceledEvent() {
        this.eventType = EventTypes.CANCELED;
    }
}
