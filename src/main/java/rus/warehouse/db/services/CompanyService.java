package rus.warehouse.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rus.warehouse.db.models.Company;
import rus.warehouse.db.repositories.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    public Optional<List<Company>> getAll(){
        return companyRepository.findAllByOrderByIdDesc();
    }
}
