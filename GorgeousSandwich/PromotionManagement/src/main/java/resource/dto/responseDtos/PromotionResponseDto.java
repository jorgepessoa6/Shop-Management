package resource.dto.responseDtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PromotionResponseDto {

    private UUID id;

    private String startDate;

    private String endDate;

    private double discountPercentage;

    private UUID shopId;

    private List<UUID> sandwichIds;

    @Override
    public String toString() {
        return "PromotionResponseDto{" +
                "id=" + id +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", discountPercentage=" + discountPercentage +
                ", shopId=" + shopId +
                ", sandwichIds=" + sandwichIds +
                '}';
    }
}
