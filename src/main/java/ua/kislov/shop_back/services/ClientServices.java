package ua.kislov.shop_back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kislov.shop_back.model.ShopClient;
import ua.kislov.shop_back.repositories.ClientRepositories;


@Service
@Transactional(readOnly = true)
public class ClientServices {

    private final ClientRepositories clientRepositories;

    @Autowired
    public ClientServices(ClientRepositories clientRepositories) {
        this.clientRepositories = clientRepositories;
    }

    public boolean isExistsById(long id){
        return clientRepositories.existsById(id);
    }

    public boolean isExistsByEmail(String email){
        return clientRepositories.existsByEmail(email);
    }

    @Transactional
    public void save(ShopClient shopClient){
        clientRepositories.save(shopClient);
    }
}
