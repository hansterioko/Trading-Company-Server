package rus.warehouse.db.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rus.warehouse.db.models.Order;
import rus.warehouse.db.models.Purchase;

import java.time.LocalDateTime;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
    // Вывод данных без фильтра по клиентам
    Page<Order> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate, PageRequest pageRequest);
    Page<Order> findByDateBefore(LocalDateTime endDate, PageRequest pageRequest);
    Page<Order> findByDateAfter(LocalDateTime startDate, PageRequest pageRequest);
    @Query("from Order")
    Page<Order> findAllByPageRequest(PageRequest pageRequest);

    // Вывод данных с фильтром по поставщикам
    @Query("SELECT p from Order p WHERE p.userclient.id IN (:clients)")
    Page<Order> findAllByClient_idIn(@Param("clients") Integer[] clients, Pageable pageRequest);
    @Query("SELECT p from Order p WHERE p.userclient.id IN (:clients) and p.date between :start AND :finish")
    Page<Order> findByDateBetweenClient_idIn(@Param("clients") Integer[] clients, @Param("start") LocalDateTime startDate, @Param("finish") LocalDateTime endDate, Pageable pageRequest);
    @Query("SELECT p from Order p WHERE p.userclient.id IN (:clients) and p.date > :start")
    Page<Order> findByDateAfterClient_idIn(@Param("clients") Integer[] clients, @Param("start") LocalDateTime startDate, Pageable pageRequest);
    @Query("SELECT p from Order p WHERE p.userclient.id IN (:clients) and p.date < :finish")
    Page<Order> findByDateBeforeClient_idIn(@Param("clients") Integer[] clients, @Param("finish") LocalDateTime endDate, Pageable pageRequest);
}
