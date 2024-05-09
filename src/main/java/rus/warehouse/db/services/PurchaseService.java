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
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;

    public PagedDataDto<PurchaseDTO> getAllPurchase(LocalDateTime startDate, LocalDateTime endDate, PageRequest pageRequest){
        List<PurchaseDTO> newList = new ArrayList<>();

        if (Objects.isNull(startDate)) {
            if (Objects.isNull(endDate)) {
                Page<Purchase> purchaseList = purchaseRepository.findAllByOrderByIdDesc(pageRequest);

                for (Purchase purchase : purchaseList) {
                    newList.add(new PurchaseDTO(purchase.getId(), purchase.getDate(), purchase.getPrice(), purchase.getCompany().getName()));
                }

                PagedDataDto<PurchaseDTO> pagedDataDto = new PagedDataDto<>();
                pagedDataDto.setData(newList);
                pagedDataDto.setTotal(purchaseList.getTotalElements());
                return pagedDataDto;
            }
            else {
                Page<Purchase> purchaseList = purchaseRepository.findByDateBefore(endDate, pageRequest);

                for (Purchase purchase : purchaseList) {
                    newList.add(new PurchaseDTO(purchase.getId(), purchase.getDate(), purchase.getPrice(), purchase.getCompany().getName()));
                }

                PagedDataDto<PurchaseDTO> pagedDataDto = new PagedDataDto<>();
                pagedDataDto.setData(newList);
                pagedDataDto.setTotal(purchaseList.getTotalElements());
                return pagedDataDto;
            }
        } else if (Objects.isNull(endDate)) {
            Page<Purchase> purchaseList = purchaseRepository.findByDateAfter(startDate, pageRequest);

            for (Purchase purchase : purchaseList) {
                newList.add(new PurchaseDTO(purchase.getId(), purchase.getDate(), purchase.getPrice(), purchase.getCompany().getName()));
            }

            PagedDataDto<PurchaseDTO> pagedDataDto = new PagedDataDto<>();
            pagedDataDto.setData(newList);
            pagedDataDto.setTotal(purchaseList.getTotalElements());
            return pagedDataDto;
        }
        else {
            Page<Purchase> purchaseList = purchaseRepository.findByDateBetween(startDate, endDate, pageRequest);

            for (Purchase purchase : purchaseList) {
                newList.add(new PurchaseDTO(purchase.getId(), purchase.getDate(), purchase.getPrice(), purchase.getCompany().getName()));
            }

            PagedDataDto<PurchaseDTO> pagedDataDto = new PagedDataDto<>();
            pagedDataDto.setData(newList);
            pagedDataDto.setTotal(purchaseList.getTotalElements());
            return pagedDataDto;
        }
    }
}
