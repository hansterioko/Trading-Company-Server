package rus.warehouse.db.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userclients")
public class UserClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String house;

    @Column(nullable = false)
    @Min(value = 0, message = "Скидка не может быть меньше 0%")
    @Max(value = 99, message = "Скидка не может быть более 99%")
    private Integer discount;

    @OneToMany(mappedBy = "userclient", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders;
}
