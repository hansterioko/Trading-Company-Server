package rus.warehouse.db.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rus.warehouse.db.models.Company;
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

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Company company, BindingResult bindingResult){
        try{
            companyService.createCompany(company);
            return ResponseEntity.ok("Компания создана");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Произошла ошибка");
        }
    }
}
