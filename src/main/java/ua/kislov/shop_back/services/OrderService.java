package ua.kislov.shop_back.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kislov.shop_back.model.OrderProduct;
import ua.kislov.shop_back.model.ShopOrder;
import ua.kislov.shop_back.model.ShoppingCartItem;
import ua.kislov.shop_back.repositories.ShopOrderRepository;
import ua.kislov.shop_back.repositories.ShoppingCartItemRepository;
import ua.kislov.shop_back.services.interfaces.OrderInterface;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService implements OrderInterface {

    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final ShopOrderRepository shopOrderRepository;
    private final CartService cartService;
    private final ClientServices clientServices;

    @Autowired
    public OrderService(ShoppingCartItemRepository shoppingCartItemRepository, ShopOrderRepository shopOrderRepository, CartService cartService, ClientServices clientServices) {
        this.shoppingCartItemRepository = shoppingCartItemRepository;
        this.shopOrderRepository = shopOrderRepository;
        this.cartService = cartService;
        this.clientServices = clientServices;
    }

    @Override
    @Transactional
    public void makeOrder(long clientId) {
        List<ShoppingCartItem> itemList = shoppingCartItemRepository.findAllByShopClientId(clientId);
        ShopOrder shopOrder = new ShopOrder();
        List<OrderProduct> orderProducts = itemList.stream()
                .map(this::fromItemToOrder).toList();
        orderProducts.forEach(e-> e.setShopOrder(shopOrder));
        shopOrder.setOrderProductsList(orderProducts);
        shopOrder.setShopClient(clientServices.getProxy(clientId));

        shopOrderRepository.save(shopOrder);
        cartService.deleteAllFromCart(clientId);
    }
    private OrderProduct fromItemToOrder(ShoppingCartItem item){
        OrderProduct product = new OrderProduct();
        product.setProduct(item.getProduct());
        product.setQuantity(item.getQuantity());

        return product;
    }
}
