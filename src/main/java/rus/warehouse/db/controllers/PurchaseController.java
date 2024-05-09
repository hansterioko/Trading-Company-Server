package rus.warehouse.db.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rus.warehouse.db.services.PurchaseService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    @GetMapping()
    public ResponseEntity getAll(@RequestParam(name = "from", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
                                 LocalDateTime startDate, @RequestParam(name = "to", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
                                 LocalDateTime endDate,
                                 @RequestParam(name = "page", defaultValue = "0") int page){
        try {
            return new ResponseEntity<>(purchaseService.getAllPurchase(startDate, endDate, PageRequest.of(page, 14, Sort.by(Sort.Direction.DESC, "date"))), HttpStatus.OK);
       //     return new ResponseEntity<>(purchaseService.getAllPurchase(null), HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Произошла ошибка");
        }
    }
}
