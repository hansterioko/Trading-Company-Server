package rus.warehouse.db.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rus.warehouse.db.services.Order_ListService;



@RestController
@RequestMapping("orders/details")
public class Order_ListController {
    @Autowired
    Order_ListService orderListService;

    @GetMapping
    public ResponseEntity getAll(@RequestParam(name = "idOrder") Integer idOrder){
        try {
            System.out.println("ID TUT");
            return new ResponseEntity<>(orderListService.getDetailOrder(idOrder), HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Произошла ошибка");
        }
    }
}
