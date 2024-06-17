package rus.warehouse.db.modelsDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.grammars.ordering.OrderingParser;
import rus.warehouse.db.models.UserClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    private BigDecimal price;

    private BigDecimal costWithVAT;

    private UserClient userClient;

    private List<OrderProduct> productList;
}
