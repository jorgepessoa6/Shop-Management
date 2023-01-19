package resource.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderItemResponseDto {

    private UUID id;
    private int quantity;
    private UUID sandwichId;
}
