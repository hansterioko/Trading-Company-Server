package rus.warehouse.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rus.warehouse.db.repositories.Order_ListRepository;

@Service
public class Order_ListService {
    @Autowired
    Order_ListRepository orderListRepository;


}
