package rus.warehouse.db.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rus.warehouse.db.models.Product;
import rus.warehouse.db.models.Purchase;
import rus.warehouse.db.modelsDTO.PurchaseProduct;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Вывод товаров от конкретной компании
//    Optional<Product> findByCompanyId(Integer id);

    // Вывод товаров, где в названии есть указанная часть
    Optional<Product> findByNameContainingIgnoreCase(String partOfName);

    // Просто вывод по ID по уменьшению
    Optional<Product> findAllByOrderByIdDesc();


    // Без поиска по имени
    //@Query("SELECT p FROM Peosuct p WHERE p.dateExpiration BEFORE :nowTime OR p.dateExpiration EQUAL :nowTime")
    Page<Product> findAllByDateExpirationLessThanEqual(PageRequest pageRequest, @Param("nowTime") LocalDateTime nowTime);

    //С поиском по имени
//    @Query("SELECT p FROM Peosuct p WHERE (p.dateExpiration BEFORE :nowTime OR p.dateExpiration EQUAL :nowTime) and p.name LIKE :search")
    Page<Product> findAllByDateExpirationLessThanEqualAndNameContainingIgnoreCase(PageRequest pageRequest, @Param("nowTime") LocalDateTime nowTime,
                                                     @Param("search") String search);

    // Без поиска
    @Query("from Product")
    Page<Product> findAllByPageRequest(PageRequest pageRequest);

    // C поиском
   // @Query("SELECT p from Product p WHERE p.name LIKE '%:search%'")
    Page<Product> findAllByNameContainingIgnoreCase(PageRequest pageRequest, @Param("search") String search);
    @Query(nativeQuery = true, value = "SELECT * FROM products p WHERE p.name = ?1 and p.vat = ?2 and p.category = ?3 and " +
            "p.characteristic = ?4 and p.type_packaging = ?5 and p.unit = ?6 and p.price = ?7 and p.date_expiration = ?8 and " +
            "p.date_of_manufacture = ?9")
    Optional<Product> findIdentical(String name, int vat, String category, String characteristic, String typePackaging,
                                    String unit, BigDecimal price, LocalDateTime dateExpiration, LocalDateTime dateOfManufacture);
}
