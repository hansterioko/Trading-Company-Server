package rus.warehouse.db.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_lists")
public class Order_List {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Positive
    @Min(value = 1, message = "Минимальное кол-во товаров для закупки - 1")
//    @Max(value = 100000, message = "Максимальное кол-во товаров для закупки - 100000")
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Order_List(Integer count, Order order, Product product) {
        this.count = count;
        this.order = order;
        this.product = product;
    }
}
