package rus.warehouse.db.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rus.warehouse.db.services.ProductService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping()
    public ResponseEntity getAll(@RequestParam(name = "pageIndex") int page,
                                 @RequestParam(name = "isChecked", defaultValue = "NO") String isChecked,
                                 @RequestParam(name = "search") String search,
                                 @RequestParam(name = "typeSorting") String typeSorting,
                                 @RequestParam(name = "sortBy", defaultValue = "DESC") String sortBy){
        try {
            //System.out.println(listProviders);
            String typeSort = "";
            switch (typeSorting){
                case "BYID":
                    typeSort = "id";
                    break;
                case "BYDATEEXPIRATED":
                    typeSort = "dateExpiration";
                    break;
                case "BYDATEMANUFACTURE":
                    typeSort = "dateOfManufacture";
                    break;
                case "BYPRICE":
                    typeSort = "price";
                    break;
            }

            if (sortBy.equals("DESC")){
                return new ResponseEntity<>(productService.getAll(isChecked, search, PageRequest.of(page, 14, Sort.by(Sort.Direction.DESC, typeSort))), HttpStatus.OK);
            }
            else { // ELSE ASC
                return new ResponseEntity<>(productService.getAll(isChecked, search, PageRequest.of(page, 14, Sort.by(Sort.Direction.ASC, typeSort))), HttpStatus.OK);
                //return new ResponseEntity<>(purchaseService.getAllPurchase(startDate, endDate, PageRequest.of(page, 14, Sort.by("date").ascending()), listProviders), HttpStatus.OK);
            }
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Произошла ошибка");
        }
    }
}
