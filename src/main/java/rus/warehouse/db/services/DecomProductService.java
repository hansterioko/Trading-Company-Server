package rus.warehouse.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rus.warehouse.db.models.DecomProduct;
import rus.warehouse.db.models.Product;
import rus.warehouse.db.modelsDTO.PagedDataDto;
import rus.warehouse.db.repositories.DecomProductRepository;
import rus.warehouse.db.repositories.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DecomProductService {
    @Autowired
    DecomProductRepository decomProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    public DecomProduct transferProduct (Integer idProduct, Integer count, String summery){
        DecomProduct decomProduct = new DecomProduct(productRepository.getById(idProduct), summery, count, LocalDateTime.now());

        productService.disCount(productRepository.getById(idProduct), count);
        return decomProductRepository.save(decomProduct);
    }

    public List<DecomProduct> getAll(){
        return decomProductRepository.findAll();
    }

    public PagedDataDto<DecomProduct> getByDate(LocalDateTime start, LocalDateTime finish){
        return null;
    }
}
