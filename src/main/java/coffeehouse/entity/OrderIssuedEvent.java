package coffeehouse.entity;

import coffeehouse.model.EventTypes;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "events")
public class OrderIssuedEvent extends OrderEvent {
    public OrderIssuedEvent() {
        this.eventType = EventTypes.ISSUED;
    }
}

