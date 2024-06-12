package rus.warehouse.db.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private BigDecimal costWithVAT;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
//    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Purchase_List> purchase_list;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public Purchase(LocalDateTime date, BigDecimal price, BigDecimal costWithVAT, Company company) {
        this.date = date;
        this.price = price;
        this.costWithVAT = costWithVAT;
        this.company = company;
    }
}
