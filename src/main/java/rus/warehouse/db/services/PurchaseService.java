package rus.warehouse.db.services;

import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import rus.warehouse.db.models.Purchase;
import rus.warehouse.db.repositories.PurchaseRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static jdk.dynalink.linker.support.Guards.isNull;

@Service
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;

    public Optional<List<Purchase>> getAllPurchase(LocalDateTime startDate, LocalDateTime endDate){
        System.out.println(startDate);
        System.out.println(endDate);
        if (Objects.isNull(startDate)) {
            System.out.println("START YES");
            if (Objects.isNull(endDate)) {
                System.out.println("END YES");
                return purchaseRepository.findAllByOrderByIdDesc();
            }
            else {
                System.out.println("3 YES");
                return purchaseRepository.findByDateBefore(endDate);
            }
        } else if (Objects.isNull(endDate)) {
            System.out.println("END2 YES");
            return purchaseRepository.findByDateAfter(startDate);
        }
        else {
            System.out.println("NO YES");
            return purchaseRepository.findByDateBetween(startDate, endDate);
        }
    }

//    public List<Purchase> getAllPurchase(LocalDate startDate){
//        return purchaseRepository.findByDate(startDate).get();
//    }
}
