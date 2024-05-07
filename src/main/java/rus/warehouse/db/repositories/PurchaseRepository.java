package rus.warehouse.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rus.warehouse.db.models.Purchase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    Optional<List<Purchase>> findByDateBetween(LocalDateTime startDate, LocalDateTime EndDate);
    Optional<List<Purchase>> findByDateBefore(LocalDateTime EndDate);
    Optional<List<Purchase>> findByDateAfter(LocalDateTime startDate);
    Optional<List<Purchase>> findAllByOrderByIdDesc();


    //Optional<List<Purchase>> findByDate(LocalDate startDate);
}
