package rus.warehouse.db.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @Min(value = 1, message = "Стоимость поставки не подсчитана")
    private BigDecimal price;
    private BigDecimal costWithVat;
    @ManyToOne
    @JoinColumn(name = "userclient_id", nullable = false)
    private UserClient userclient;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Order_List> order_lists;
    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    public Order(Integer id, BigDecimal price, BigDecimal costWithVat, UserClient userclient, LocalDateTime date) {
        this.id = id;
        this.price = price;
        this.costWithVat = costWithVat;
        this.userclient = userclient;
        this.date = date;
    }

    public Order(BigDecimal price, BigDecimal costWithVat, UserClient userclient, LocalDateTime date) {
        this.price = price;
        this.costWithVat = costWithVat;
        this.userclient = userclient;
        this.order_lists = order_lists;
        this.date = date;
    }
}
