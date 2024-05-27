package rus.warehouse.db.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @Min(value = 1, message = "Стоимость поставки не подсчитана")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "userclient_id", nullable = false)
    private UserClient userclient;
    @Column(nullable = false)
    private String status;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Order_List> order_lists;
    @Column(nullable = false)
    private LocalDateTime date;
}
