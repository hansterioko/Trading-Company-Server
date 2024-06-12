package rus.warehouse.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rus.warehouse.db.models.DecomProduct;

@Repository
public interface DecomProductRepository extends JpaRepository<DecomProduct, Integer> {

}
