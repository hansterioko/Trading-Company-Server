package rus.warehouse.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rus.warehouse.db.models.Company;
import rus.warehouse.db.models.UserClient;
import rus.warehouse.db.repositories.UserClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserClientService {
    @Autowired
    UserClientRepository userClientRepository;

    public Optional<List<UserClient>> getAll(){
        return userClientRepository.findAllByOrderByIdDesc();
    }

    public UserClient createClient(UserClient client){
        System.out.println(client);
        if (userClientRepository.findByNameIgnoreCase(client.getName()) != null){
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            return userClientRepository.save(client);
        }
    }
}
