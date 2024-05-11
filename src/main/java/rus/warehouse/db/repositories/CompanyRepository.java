package rus.warehouse.db.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rus.warehouse.db.models.Company;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {
    // Вывод компаний, где в названии есть указанная часть
    Optional<Company> findByNameContainingIgnoreCase(String partOfName);

    // Просто вывод по ID по уменьшению ИД
    Optional<List<Company>> findAllByOrderByIdDesc();
}
