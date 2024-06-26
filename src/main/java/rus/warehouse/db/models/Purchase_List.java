package rus.warehouse.db.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "purchase_lists")
public class Purchase_List {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @Min(value = 1, message = "Минимальное кол-во товаров для закупки - 1")
//    @Max(value = 100000, message = "Максимальное кол-во товаров для закупки - 100000")
    private Integer count;
//    @Column(nullable = false)
//    @Positive
//    @Min(value = 1, message = "Минимальная цена товара - 1")
//    private Integer price;
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    @JsonIgnore
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Purchase_List(Integer count, Purchase purchase, Product product) {
        this.count = count;
        this.purchase = purchase;
        this.product = product;
    }
}
