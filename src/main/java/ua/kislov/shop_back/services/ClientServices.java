package ua.kislov.shop_back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kislov.shop_back.exception.UserNotFoundException;
import ua.kislov.shop_back.model.ShopClient;
import ua.kislov.shop_back.repositories.ClientRepository;


@Service
@Transactional(readOnly = true)
public class ClientServices {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServices(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean isExistsById(long id) {
        return clientRepository.existsById(id);
    }

    public boolean isExistsByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }

    @Transactional
    public void save(ShopClient shopClient) {
        clientRepository.save(shopClient);
    }

    public ShopClient getClient(long id){
        return clientRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User is not found"));
    }

    public ShopClient getProxy(long id){
        return clientRepository.getReferenceById(id);
    }
}
