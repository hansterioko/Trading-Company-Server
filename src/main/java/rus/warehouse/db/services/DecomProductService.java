package rus.warehouse.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rus.warehouse.db.models.DecomProduct;
import rus.warehouse.db.models.Product;
import rus.warehouse.db.modelsDTO.PagedDataDto;
import rus.warehouse.db.repositories.DecomProductRepository;

import java.time.LocalDateTime;

@Service
public class DecomProductService {
    @Autowired
    DecomProductRepository decomProductRepository;

    public DecomProduct transferProduct (Product product, Integer count, LocalDateTime date){
        return null;
    }

    public PagedDataDto<DecomProduct> getByDate(LocalDateTime start, LocalDateTime finish){
        return null;
    }
}
