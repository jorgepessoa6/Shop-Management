package resource.dto.responseDtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class StorageResponseDto {

    private UUID id;
    private int quantity;
    private UUID sandwichId;

}
