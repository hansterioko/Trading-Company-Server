package rus.warehouse.db.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rus.warehouse.db.models.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
