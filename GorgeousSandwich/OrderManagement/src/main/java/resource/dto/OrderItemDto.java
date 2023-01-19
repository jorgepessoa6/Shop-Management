package resource.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.UUID;

@Getter
@Setter
public class OrderItemDto {

    @Min(value = 0L, message = "The quantity must be positive")
    private int quantity;
    private UUID sandwichId;

}
