package rus.warehouse.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import rus.warehouse.db.models.Product;
import rus.warehouse.db.models.Purchase;
import rus.warehouse.db.modelsDTO.PagedDataDto;
import rus.warehouse.db.modelsDTO.PurchaseDTO;
import rus.warehouse.db.modelsDTO.PurchaseProduct;
import rus.warehouse.db.repositories.ProductRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public PagedDataDto getAll(String isChecked, String search, PageRequest pageRequest){
        if (search.trim().isEmpty()){
            if (isChecked.equals("YES")){
                return convertPageToDTO(productRepository.findAllByDateExpirationLessThanEqual(pageRequest, LocalDateTime.now()));
            }
            else {
                return convertPageToDTO(productRepository.findAllByPageRequest(pageRequest));
            }
        }
        else{
            if (isChecked.equals("YES")){
                return convertPageToDTO(productRepository.findAllByDateExpirationLessThanEqualAndNameContainingIgnoreCase(pageRequest, LocalDateTime.now(), search));
            }
            else {
                return convertPageToDTO(productRepository.findAllByNameContainingIgnoreCase(pageRequest, search));
            }
        }
    }

    private PagedDataDto<Product> convertPageToDTO(Page<Product> productList){
        List<Product> newList = new ArrayList<>();

        for (Product product : productList) {
            newList.add(product);
        }

        PagedDataDto<Product> pagedDataDto = new PagedDataDto<>();
        pagedDataDto.setData(newList);
        pagedDataDto.setTotal(productList.getTotalElements());
        return pagedDataDto;
    }

    public Product createProduct(PurchaseProduct product) {
        //System.out.println("ПРИШЁЛЁЛЁЛЁЛЁЛЁЁЛЛ " + product);

        Optional<Product> returnProduct = productRepository.findIdentical(product.getName(), product.getVat(), product.getCategory(), product.getCharacteristic(),
                product.getTypePackaging(), product.getUnit(), product.getPrice(), product.getDateOfExpiration(),
                product.getManufactureDate());
        if (returnProduct.isPresent()) {
            //System.out.println("ТОВАР ЕСТЬ!!!");
            returnProduct.get().setCountOnWarehouse(returnProduct.get().getCountOnWarehouse() + product.getCount());
            return productRepository.save(returnProduct.get());
        }
        else {
            Product newProduct = new Product(product.getName(), product.getVat(), product.getCategory(), product.getCharacteristic(),
                    product.getTypePackaging(), product.getUnit(), product.getPrice(), product.getDateOfExpiration(),
                    product.getManufactureDate());
            newProduct.setCountOnWarehouse(product.getCount());
            //System.out.println("Добавил ТОВАР ОДИн раз");
            return productRepository.save(newProduct);
        }
    }

    public Product disCount(Product product, Integer count){
        product.setCountOnWarehouse(product.getCountOnWarehouse() - count);
        return productRepository.save(product);
    }
}
