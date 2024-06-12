package rus.warehouse.db.modelsDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseProduct {
    private String name;
    private int vat;
    private String category;
    private String typePackaging;
    private String characteristic;
    private String unit;
    private BigDecimal price;
    private Integer count;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime manufactureDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime dateOfExpiration;
}
