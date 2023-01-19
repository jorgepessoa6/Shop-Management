package resource.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderDto {
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/([0-9]{4}) ([01][0-9]|2[0-3]):[0-5][0-9]$")
    private String deliveryDate;
    private List<OrderItemDto> orderItemDto;
    private UUID shopId;
    private UUID userId;
}
