package rus.warehouse.db.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rus.warehouse.db.models.Carwash;

@Repository
public interface CarwashRepository extends CrudRepository<Carwash, Integer> {
}
