package coffeehouse.entity;

import coffeehouse.model.EventTypes;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table()
public class OrderAcceptedEvent extends OrderEvent {
    public OrderAcceptedEvent() {
        this.eventType = EventTypes.ACCEPTED;
    }
}
