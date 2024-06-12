package rus.warehouse.db.modelsDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rus.warehouse.db.models.Company;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPurchaseDTO {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    private BigDecimal price;

    private BigDecimal costWithVAT;

    private Company company;

    private List<PurchaseProduct> productList;

}
