package rus.warehouse.db.modelsDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PagedDataDto<T> {
    private List<T> data;

    private long total;
}
