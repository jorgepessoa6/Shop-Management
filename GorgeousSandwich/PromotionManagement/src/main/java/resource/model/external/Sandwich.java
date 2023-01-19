package resource.model.external;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Sandwich {

    private UUID id;

    private String designation;

    private float price;

    private final List<SandwichDescription> sandwichDescriptionList = new ArrayList<>();

    @Override
    public String toString() {
        return "Sandwich{" +
                "id=" + id +
                ", designation='" + designation + '\'' +
                ", price=" + price +
                ", sandwichDescriptionList=" + sandwichDescriptionList +
                '}';
    }
}
