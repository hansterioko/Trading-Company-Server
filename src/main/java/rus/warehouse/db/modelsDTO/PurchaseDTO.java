package rus.warehouse.db.modelsDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import rus.warehouse.db.models.Purchase_List;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class PurchaseDTO {
    private Integer id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    private Integer price;

    private String company;
}
