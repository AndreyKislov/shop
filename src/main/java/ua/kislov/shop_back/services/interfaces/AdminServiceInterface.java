package ua.kislov.shop_back.services.interfaces;

import ua.kislov.shop_back.model.Product;
import ua.kislov.shop_back.model.ShopClient;

public interface AdminServiceInterface {
    ShopClient shopClient(long id);

    void deleteShopClient(long id);

    Product product(long id);

    void saveNewProduct(Product product);

    Boolean productIsExistsByName(String name);

}
