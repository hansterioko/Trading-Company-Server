package rus.warehouse.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rus.warehouse.db.models.Purchase;
import rus.warehouse.db.models.Purchase_List;
import rus.warehouse.db.modelsDTO.Purchase_listDTO;
import rus.warehouse.db.repositories.ProductRepository;
import rus.warehouse.db.repositories.PurchaseRepository;
import rus.warehouse.db.repositories.Purchase_ListRepository;

import java.util.List;

@Service
public class Purchase_ListService {
    @Autowired
    Purchase_ListRepository purchase_listRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    ProductService productService;

    public void create(Purchase_List purchase_list){
        try {
            purchase_listRepository.save(purchase_list);
        }
        catch (Exception e){
            System.out.println("Ошибка в добавлении листа закупки");
        }
    }

    public Purchase_listDTO getDetailPurchase(Integer idPurchase){
        Purchase purchase = purchaseRepository.findById(idPurchase).get();

        List<Purchase_List> purchase_lists = purchase_listRepository.findByPurchaseId(idPurchase);
        for (Purchase_List pList:
             purchase_lists) {
            //System.out.println(pList.getProduct());
        }
        return new Purchase_listDTO(purchase_lists, purchase);
    }
}
