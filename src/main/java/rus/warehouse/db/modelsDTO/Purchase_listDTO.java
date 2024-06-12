package rus.warehouse.db.modelsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rus.warehouse.db.models.Purchase;
import rus.warehouse.db.models.Purchase_List;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase_listDTO {
    private List<Purchase_List> prodLists;

    private Purchase purchase;
}
