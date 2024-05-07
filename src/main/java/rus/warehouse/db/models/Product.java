package rus.warehouse.db.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int vat;
    @Column(nullable = false)
    private String category;
    private String characteristic;
    @Column(nullable = false)
    private String Unit;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private Integer countOnWarehouse;

// VREMENNNNNNNOOOOO    @Column(nullable = false)
    private String photo;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order_List> order_lists;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Purchase_List> purchaseList;
}
