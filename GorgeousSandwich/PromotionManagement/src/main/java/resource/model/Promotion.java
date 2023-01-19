package resource.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="Promotions")
@Getter
@Setter
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Date startDate;

    private Date endDate;

    private double discountPercentage;

    @Column(unique = true)
    private UUID shopId;

    @ElementCollection
    @CollectionTable(name = "sandwichIds")
    @Column(name = "sandwichId")
    private List<UUID> sandwichIds = new ArrayList<>();
}
