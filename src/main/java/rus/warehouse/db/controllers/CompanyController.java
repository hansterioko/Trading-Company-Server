package rus.warehouse.db.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rus.warehouse.db.services.CompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping()
    public ResponseEntity getAll(){
        try {
            return new ResponseEntity<>(companyService.getAll().get(), HttpStatus.OK);
            //     return new ResponseEntity<>(purchaseService.getAllPurchase(null), HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Произошла ошибка");
        }
    }
}
