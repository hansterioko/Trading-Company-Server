package rus.warehouse.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import rus.warehouse.db.models.*;
import rus.warehouse.db.modelsDTO.*;
import rus.warehouse.db.repositories.OrderRepository;
import rus.warehouse.db.repositories.Order_ListRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    Order_ListService orderListRepository;

    @Autowired
    ProductService productService;

    public PagedDataDto<Order> getAllOrder(LocalDateTime startDate, LocalDateTime endDate, PageRequest pageRequest, String listClients){
        String[] listClient = new String[0];
        // System.out.println(listProviders);
        if (!Objects.isNull(listClients)){
            listClient = listClients.split(",");
            //System.out.println(listClients);
        }


        if(listClient.length > 0){
            System.out.println(listClient);
            Integer[] numbers = new Integer[listClient.length];
            for(int i = 0;i < listClient.length;i++)
            {
                try
                {
                    numbers[i] = Integer.parseInt(listClient[i]);
                }
                catch (NumberFormatException nfe)
                {
                    numbers[i] = null;
                }
            }
            //System.out.println("IN");
            if (Objects.isNull(startDate)) {
                if (Objects.isNull(endDate)) {
                    return convertPageToDTO(orderRepository.findAllByClient_idIn(numbers, pageRequest));
                }
                else {
                    return convertPageToDTO(orderRepository.findByDateBeforeClient_idIn(numbers, endDate, pageRequest));
                }
            } else if (Objects.isNull(endDate)) {
                return convertPageToDTO(orderRepository.findByDateAfterClient_idIn(numbers, startDate, pageRequest));
            }
            else {
                return convertPageToDTO(orderRepository.findByDateBetweenClient_idIn(numbers, startDate, endDate, pageRequest));
            }
        }
        else {
            //System.out.println("NOT IN");
            if (Objects.isNull(startDate)) {
                if (Objects.isNull(endDate)) {
                    return convertPageToDTO(orderRepository.findAllByPageRequest(pageRequest));
                }
                else {
                    return convertPageToDTO(orderRepository.findByDateBefore(endDate, pageRequest));
                }
            } else if (Objects.isNull(endDate)) {
                return convertPageToDTO(orderRepository.findByDateAfter(startDate, pageRequest));
            }
            else {
                return convertPageToDTO(orderRepository.findByDateBetween(startDate, endDate, pageRequest));
            }
        }
    }

    private PagedDataDto<Order> convertPageToDTO(Page<Order> orderList){
        System.out.println(orderList);
        List<Order> newList = new ArrayList<>();

        for (Order order : orderList) {
            newList.add(new Order(order.getId(), order.getPrice(), order.getCostWithVat(), order.getUserclient(), order.getDate()));
        }

        PagedDataDto<Order> pagedDataDto = new PagedDataDto<>();
        pagedDataDto.setData(newList);
        pagedDataDto.setTotal(orderList.getTotalElements());
        return pagedDataDto;
    }

    public Order createOrder(OrderDTO orderDTO){
        Order order = new Order(orderDTO.getPrice(), orderDTO.getCostWithVAT(),
                orderDTO.getUserClient(), orderDTO.getDate());
        order = orderRepository.save(order);

        for (OrderProduct prod:
                orderDTO.getProductList()) {
            Product product = new Product(prod.getId(), prod.getName(), prod.getVat(), prod.getCategory()
                    , prod.getCharacteristic(), prod.getTypePackaging(), prod.getUnit(), prod.getPrice()
            , prod.getDateOfExpiration(), prod.getManufactureDate(), prod.getCountOnWarehouse());
            //System.out.println("ЦИКЛ АТКИНСОНА");
            product = productService.disCount(product, prod.getCount());

            orderListRepository.create(new Order_List(prod.getCount(), order, product));
        }

        return order;
    }
}
