package ua.kislov.shop_back.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kislov.shop_back.exceptions.OrdersNotFoundException;
import ua.kislov.shop_back.model.OrderProduct;
import ua.kislov.shop_back.model.OrderStatus;
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
    private final ClientService clientService;

    @Autowired
    public OrderService(ShoppingCartItemRepository shoppingCartItemRepository, ShopOrderRepository shopOrderRepository, CartService cartService, ClientService clientService) {
        this.shoppingCartItemRepository = shoppingCartItemRepository;
        this.shopOrderRepository = shopOrderRepository;
        this.cartService = cartService;
        this.clientService = clientService;
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
        shopOrder.setShopClient(clientService.getProxy(clientId));
        shopOrder.setOrderStatus(OrderStatus.NEW);

        shopOrderRepository.save(shopOrder);
        cartService.deleteAllFromCart(clientId);
    }

    @Override
    public List<ShopOrder> getOrders() {
        return shopOrderRepository.findAllWhereStatusIsNew(OrderStatus.NEW);
    }

    @Override
    public ShopOrder getOrder(long id) {
        return shopOrderRepository.findById(id)
                .orElseThrow(() -> new OrdersNotFoundException("Order is not found."));
    }

    @Override
    @Transactional
    public void completedOrder(long id) {
        shopOrderRepository.exchangeStatus(id, OrderStatus.REFURBISHED);
    }

    private OrderProduct fromItemToOrder(ShoppingCartItem item){
        OrderProduct product = new OrderProduct();
        product.setProduct(item.getProduct());
        product.setQuantity(item.getQuantity());
        return product;
    }
}
