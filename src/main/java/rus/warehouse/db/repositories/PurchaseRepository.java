package rus.warehouse.db.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    // Вывод данных без фильтра по поставщикам
    Page<Purchase> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate, PageRequest pageRequest);
    Page<Purchase> findByDateBefore(LocalDateTime endDate, PageRequest pageRequest);
    Page<Purchase> findByDateAfter(LocalDateTime startDate, PageRequest pageRequest);
    @Query("from Purchase")
    Page<Purchase> findAllByPageRequest(PageRequest pageRequest);

    // Вывод данных с фильтром по поставщикам
    @Query("SELECT p from Purchase p WHERE p.company.id IN (:companies)")
    Page<Purchase> findAllByCompany_idIn(@Param("companies") Integer[] companies, Pageable pageRequest);
    @Query("SELECT p from Purchase p WHERE p.company.id IN (:companies) and p.date between :start AND :finish")
    Page<Purchase> findByDateBetweenCompany_idIn(@Param("companies") Integer[] companies, @Param("start") LocalDateTime startDate, @Param("finish") LocalDateTime endDate, Pageable pageRequest);
    @Query("SELECT p from Purchase p WHERE p.company.id IN (:companies) and p.date > :start")
    Page<Purchase> findByDateAfterCompany_idIn(@Param("companies") Integer[] companies, @Param("start") LocalDateTime startDate, Pageable pageRequest);
    @Query("SELECT p from Purchase p WHERE p.company.id IN (:companies) and p.date < :finish")
    Page<Purchase> findByDateBeforeCompany_idIn(@Param("companies") Integer[] companies, @Param("finish") LocalDateTime endDate, Pageable pageRequest);
}
