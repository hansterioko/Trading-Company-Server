package rus.warehouse.db.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    @Min(value = 1, message = "Стоимость не указана")
    private Integer price;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
//    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Purchase_List> purchase_list;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
