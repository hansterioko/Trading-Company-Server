package rus.warehouse.db.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rus.warehouse.db.models.Company;
import rus.warehouse.db.models.UserClient;
import rus.warehouse.db.repositories.UserClientRepository;
import rus.warehouse.db.services.UserClientService;

@RestController
@RequestMapping("/clients")
public class UserClientController {
    @Autowired
    UserClientService userClientService;

    @GetMapping()
    public ResponseEntity getAll(){
        try {
            return new ResponseEntity<>(userClientService.getAll().get(), HttpStatus.OK);
            //     return new ResponseEntity<>(purchaseService.getAllPurchase(null), HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Произошла ошибка");
        }
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody UserClient client, BindingResult bindingResult){
        try{
            userClientService.createClient(client);
            return ResponseEntity.ok("Получатель создан");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Произошла ошибка");
        }
    }
}
