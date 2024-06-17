package rus.warehouse.db.modelsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rus.warehouse.db.models.Order;
import rus.warehouse.db.models.Order_List;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order_ListDTO {
    private List<Order_List> prodLists;

    private Order order;
}
