package resource.model.external;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Promotion {

    private UUID id;

    private Date startDate;

    private Date endDate;

    private double discountPercentage;

    private UUID shopId;

    private List<UUID> sandwichIds;


}
