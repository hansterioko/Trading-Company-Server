package rus.warehouse.db.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rus.warehouse.db.models.UserClient;

@Repository
public interface UserClientRepository extends CrudRepository<UserClient, Integer> {
}
