package rus.warehouse.db.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rus.warehouse.db.models.Company;
import rus.warehouse.db.modelsDTO.DecomProductDTO;
import rus.warehouse.db.services.DecomProductService;

@RestController
@RequestMapping("/decomprod")
public class DecomProductController {
    @Autowired
    DecomProductService decomProductService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody DecomProductDTO decomProductDTO, BindingResult bindingResult){
        try{
            decomProductService.transferProduct(decomProductDTO.getIdProduct(), decomProductDTO.getDecCount(), decomProductDTO.getComment());
            return ResponseEntity.ok("Компания создана");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Произошла ошибка");
        }
    }

    @GetMapping()
    public ResponseEntity getAll(){
        try {
            return new ResponseEntity<>(decomProductService.getAll(), HttpStatus.OK);
            //     return new ResponseEntity<>(purchaseService.getAllPurchase(null), HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Произошла ошибка");
        }
    }


}
