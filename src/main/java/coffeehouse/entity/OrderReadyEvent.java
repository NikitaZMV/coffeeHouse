
package coffeehouse.entity;

import coffeehouse.model.EventTypes;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "events")
public class OrderReadyEvent extends OrderEvent {
    public OrderReadyEvent() {
        this.eventType = EventTypes.READY;
    }
}