package rus.warehouse.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rus.warehouse.db.models.Order_List;

import java.util.List;

@Repository
public interface Order_ListRepository extends JpaRepository<Order_List, Integer> {
    List<Order_List> findByOrderId(Integer idOrder);
}
