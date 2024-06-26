package rus.warehouse.db.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int vat;
//    @Column(nullable = false)
    private String category;
    private String characteristic;
   // @Column(nullable = false)
    private String typePackaging;
    @Column(nullable = false)
    private String unit;
    @Column(nullable = false)
    private BigDecimal price;
    // NEW COLUMN 26.05.2024
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime dateExpiration;
    // New column 26.05.2024
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime dateOfManufacture;
    //@Column(nullable = false)
    private Integer countOnWarehouse;

// VREMENNNNNNNOOOOO    @Column(nullable = false)
    //private String photo;

//    @ManyToOne
//    @JoinColumn(name = "company_id", nullable = false)
//    private Company company;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order_List> order_lists;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Purchase_List> purchaseList;

    public Product(String name, int vat, String category, String characteristic, String typePackaging, String unit, BigDecimal price, LocalDateTime dateExpiration, LocalDateTime dateOfManufacture) {
        this.name = name;
        this.vat = vat;
        this.category = category;
        this.characteristic = characteristic;
        this.typePackaging = typePackaging;
        this.unit = unit;
        this.price = price;
        this.dateExpiration = dateExpiration;
        this.dateOfManufacture = dateOfManufacture;
    }

    public Product(Integer id, String name, int vat, String category, String characteristic, String typePackaging, String unit, BigDecimal price, LocalDateTime dateOfExpiration, LocalDateTime manufactureDate, Integer countOnWarehouse) {
        this.id = id;
        this.name = name;
        this.vat = vat;
        this.category = category;
        this.characteristic = characteristic;
        this.typePackaging = typePackaging;
        this.unit = unit;
        this.price = price;
        this.dateExpiration = dateExpiration;
        this.dateOfManufacture = dateOfManufacture;
        this.countOnWarehouse = countOnWarehouse;
    }
}
