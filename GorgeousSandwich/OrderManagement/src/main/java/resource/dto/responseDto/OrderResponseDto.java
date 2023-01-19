package resource.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderResponseDto {

    private UUID id;
    private Date deliveryDate;
    private List<OrderItemResponseDto> orderItems;
    private double totalPrice;

}
