package ua.kislov.shop_back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kislov.shop_back.exception.UserNotFoundException;
import ua.kislov.shop_back.exceptions.ProductNotFoundException;
import ua.kislov.shop_back.model.Product;
import ua.kislov.shop_back.model.ShopClient;
import ua.kislov.shop_back.repositories.ClientRepository;
import ua.kislov.shop_back.repositories.ProductRepository;
import ua.kislov.shop_back.services.interfaces.AdminServiceInterface;

@Service
@Transactional(readOnly = true)
public class AdminService implements AdminServiceInterface {
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    @Autowired
    public AdminService(ClientRepository clientRepository, ProductRepository productRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ShopClient shopClient(long id) {
        return clientRepository.findById(id).orElseThrow(() -> new UserNotFoundException("ShopClient is not found"));
    }

    @Override
    @Transactional
    public void deleteShopClient(long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
        }
    }

    @Override
    public Product product(long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product is not found"));
    }

    @Override
    @Transactional
    public void saveNewProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Boolean productIsExistsByName(String name) {
        return productRepository.existsByName(name);
    }
}
