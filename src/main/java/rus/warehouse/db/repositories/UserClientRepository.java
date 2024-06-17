package rus.warehouse.db.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rus.warehouse.db.models.Company;
import rus.warehouse.db.models.UserClient;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserClientRepository extends CrudRepository<UserClient, Integer> {
    Optional<List<UserClient>> findAllByOrderByIdDesc();

    UserClient findByNameIgnoreCase(String name);
}
