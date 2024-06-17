package rus.warehouse.db.modelsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {
    private Integer id;

    private String name;

    private int vat;

    private String category;

    private String typePackaging;

    private String characteristic;

    private String unit;

    private BigDecimal price;

    private Integer count;

    private Integer countOnWarehouse;

    private LocalDateTime manufactureDate;

    private LocalDateTime dateOfExpiration;
}
