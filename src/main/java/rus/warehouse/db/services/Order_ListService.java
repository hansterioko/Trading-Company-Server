package rus.warehouse.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rus.warehouse.db.models.Order;
import rus.warehouse.db.models.Order_List;
import rus.warehouse.db.models.Purchase;
import rus.warehouse.db.models.Purchase_List;
import rus.warehouse.db.modelsDTO.Order_ListDTO;
import rus.warehouse.db.modelsDTO.Purchase_listDTO;
import rus.warehouse.db.repositories.OrderRepository;
import rus.warehouse.db.repositories.Order_ListRepository;

import java.util.List;

@Service
public class Order_ListService {
    @Autowired
    Order_ListRepository orderListRepository;

    @Autowired
    OrderRepository orderRepository;

    public void create(Order_List orderList){
        try {
            orderListRepository.save(orderList);
        }
        catch (Exception e){
            System.out.println("Ошибка в добавлении листа поставки");
        }
    }

    public Order_ListDTO getDetailOrder(Integer idOrder){
        //System.out.println(idOrder);
        Order order = orderRepository.findById(idOrder).get();
//        System.out.println(idOrder + " or " + order);
        List<Order_List> order_lists = orderListRepository.findByOrderId(idOrder);
//        for (Order_List pList:
//                order_lists) {
//            //System.out.println(pList.getProduct());
//        }

        //System.out.println(order_lists);
        return new Order_ListDTO(order_lists, order);
    }
}
