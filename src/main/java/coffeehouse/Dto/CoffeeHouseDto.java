package coffeehouse.Dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CoffeeHouseDto<T> {
    private String status = "success";
    private T payload;

    public CoffeeHouseDto(T payload) {
        this.payload = payload;
    }
    public CoffeeHouseDto(T payload, String status) {
        this.payload = payload;
        this.status = status;
    }
}
