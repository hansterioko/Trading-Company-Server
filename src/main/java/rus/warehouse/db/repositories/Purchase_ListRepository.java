package rus.warehouse.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rus.warehouse.db.models.Purchase_List;
import rus.warehouse.db.modelsDTO.Purchase_listDTO;

import java.util.List;

@Repository
public interface Purchase_ListRepository extends JpaRepository<Purchase_List, Integer> {

    //@Query("SELECT p FROM Purchase_List p WHERE p.purchase.id = :id")
    List<Purchase_List> findByPurchaseId(@Param("id") Integer id);
}
