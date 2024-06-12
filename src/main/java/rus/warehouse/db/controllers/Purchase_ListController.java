package rus.warehouse.db.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rus.warehouse.db.services.Purchase_ListService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/purchases/detail")
public class Purchase_ListController {
    @Autowired
    Purchase_ListService purchaseListService;

    @GetMapping
    public ResponseEntity getAll(@RequestParam(name = "idPurchase") Integer idPurchase){
        try {
            return new ResponseEntity<>(purchaseListService.getDetailPurchase(idPurchase), HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Произошла ошибка");
        }
    }
}
