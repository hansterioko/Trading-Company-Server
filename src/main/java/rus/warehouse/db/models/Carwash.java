package rus.warehouse.db.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carwashes")
public class Carwash {
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

    @Column(nullable = false)
    private String key;

    @OneToMany(mappedBy = "carwash", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders;
}
