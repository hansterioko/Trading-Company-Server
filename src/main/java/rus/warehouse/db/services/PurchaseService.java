package rus.warehouse.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import rus.warehouse.db.models.Purchase;
import rus.warehouse.db.modelsDTO.PagedDataDto;
import rus.warehouse.db.modelsDTO.PurchaseDTO;
import rus.warehouse.db.repositories.PurchaseRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;

    public PagedDataDto<PurchaseDTO> getAllPurchase(LocalDateTime startDate, LocalDateTime endDate, PageRequest pageRequest, String listProviders){
        String[] listCompany = new String[0];
       // System.out.println(listProviders);
        if (!Objects.isNull(listProviders)){
            listCompany = listProviders.split(",");
            System.out.println(listCompany);
        }


        if(listCompany.length > 0){
            System.out.println(listCompany);
            Integer[] numbers = new Integer[listCompany.length];
            for(int i = 0;i < listCompany.length;i++)
            {
                try
                {
                    numbers[i] = Integer.parseInt(listCompany[i]);
                }
                catch (NumberFormatException nfe)
                {
                    numbers[i] = null;
                }
            }
            //System.out.println("IN");
            if (Objects.isNull(startDate)) {
                if (Objects.isNull(endDate)) {
                    return convertPageToDTO(purchaseRepository.findAllByCompany_idIn(numbers, pageRequest));
                }
                else {
                    return convertPageToDTO(purchaseRepository.findByDateBeforeCompany_idIn(numbers, endDate, pageRequest));
                }
            } else if (Objects.isNull(endDate)) {
                return convertPageToDTO(purchaseRepository.findByDateAfterCompany_idIn(numbers, startDate, pageRequest));
            }
            else {
                return convertPageToDTO(purchaseRepository.findByDateBetweenCompany_idIn(numbers, startDate, endDate, pageRequest));
            }
        }
        else {
            //System.out.println("NOT IN");
            if (Objects.isNull(startDate)) {
                if (Objects.isNull(endDate)) {
                    return convertPageToDTO(purchaseRepository.findAllByPageRequest(pageRequest));
                }
                else {
                    return convertPageToDTO(purchaseRepository.findByDateBefore(endDate, pageRequest));
                }
            } else if (Objects.isNull(endDate)) {
                return convertPageToDTO(purchaseRepository.findByDateAfter(startDate, pageRequest));
            }
            else {
                return convertPageToDTO(purchaseRepository.findByDateBetween(startDate, endDate, pageRequest));
            }
        }
    }

    private PagedDataDto<PurchaseDTO> convertPageToDTO(Page<Purchase> purchaseList){
        List<PurchaseDTO> newList = new ArrayList<>();

        for (Purchase purchase : purchaseList) {
            newList.add(new PurchaseDTO(purchase.getId(), purchase.getDate(), purchase.getPrice(), purchase.getCompany().getName()));
        }

        PagedDataDto<PurchaseDTO> pagedDataDto = new PagedDataDto<>();
        pagedDataDto.setData(newList);
        pagedDataDto.setTotal(purchaseList.getTotalElements());
        return pagedDataDto;
    }
}
