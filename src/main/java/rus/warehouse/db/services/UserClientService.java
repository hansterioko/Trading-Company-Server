package rus.warehouse.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rus.warehouse.db.repositories.UserClientRepository;

@Service
public class UserClientService {
    @Autowired
    UserClientRepository userClientRepository;

}
