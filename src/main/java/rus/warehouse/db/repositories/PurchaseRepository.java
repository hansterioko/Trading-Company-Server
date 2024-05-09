package rus.warehouse.db.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    Page<Purchase> findByDateBetween(LocalDateTime startDate, LocalDateTime EndDate, PageRequest pageRequest);
    Page<Purchase> findByDateBefore(LocalDateTime EndDate, PageRequest pageRequest);
    Page<Purchase> findByDateAfter(LocalDateTime startDate, PageRequest pageRequest);
    Page<Purchase> findAllByOrderByIdDesc(PageRequest pageRequest);


    //Optional<List<Purchase>> findByDate(LocalDate startDate);
}
