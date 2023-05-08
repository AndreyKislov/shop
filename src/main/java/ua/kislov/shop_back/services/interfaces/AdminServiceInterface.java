package ua.kislov.shop_back.services.interfaces;

import ua.kislov.shop_back.model.Product;
import ua.kislov.shop_back.model.ShopClient;
import ua.kislov.shop_back.model.ShopOrder;

import java.util.List;

public interface AdminServiceInterface {
    ShopClient shopClient(long id);

    void deleteShopClient(long id);

    Product product(long id);

    void saveNewProduct(Product product);

    Boolean productIsExistsByName(String name);

    List<ShopOrder> getOrders();
    ShopOrder getOrder(long id);
    void completedOrder(long id);

}
