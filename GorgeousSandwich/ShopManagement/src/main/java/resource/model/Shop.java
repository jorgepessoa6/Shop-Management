package resource.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="Shops")
@Getter
@Setter
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String address;

    private Properties properties;

    private String designation;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Schedule> schedules = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Storage> storages = new LinkedList<>();

    private UUID personInChargeId;
}
