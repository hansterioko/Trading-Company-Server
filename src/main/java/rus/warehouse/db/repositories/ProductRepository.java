package rus.warehouse.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rus.warehouse.db.models.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Вывод товаров от конкретной компании
    Optional<Product> findByCompanyId(Integer id);

    // Вывод товаров, где в названии есть указанная часть
    Optional<Product> findByNameContainingIgnoreCase(String partOfName);

    // Просто вывод по ID по уменьшению
    Optional<Product> findAllByOrderByIdDesc();
}
