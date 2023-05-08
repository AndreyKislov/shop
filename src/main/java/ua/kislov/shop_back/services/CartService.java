package ua.kislov.shop_back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kislov.shop_back.dto.CartItemsDTO;
import ua.kislov.shop_back.dto.ProductDTO;
import ua.kislov.shop_back.dto.UpdateQuantityDTO;
import ua.kislov.shop_back.model.Product;
import ua.kislov.shop_back.model.ShopClient;
import ua.kislov.shop_back.model.ShoppingCartItem;
import ua.kislov.shop_back.repositories.ShoppingCartItemRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CartService {
    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final ClientServices clientServices;
    private final ProductService productService;

    @Autowired
    public CartService(ShoppingCartItemRepository shoppingCartItemRepository, ClientServices clientServices, ProductService productService) {
        this.shoppingCartItemRepository = shoppingCartItemRepository;
        this.clientServices = clientServices;
        this.productService = productService;
    }

    public List<ProductDTO> getCart(long clientId) {
        List<ShoppingCartItem> cartItemList = shoppingCartItemRepository
                .findAllByShopClientId(clientId);
        if (!cartItemList.isEmpty())
            return cartItemList.stream()
                    .map(p -> new ProductDTO(p.getProduct(), p.getQuantity()))
                    .collect(Collectors.toList());
        else
            return Collections.emptyList();
    }

    @Transactional
    public void addToCart(CartItemsDTO dto) {
        ShopClient shopClient = clientServices.getProxy(dto.getClientId());
        Product product = productService.getProxy(dto.getProductId());
        long productId = dto.getProductId();
        long clientId = dto.getClientId();
        if (shoppingCartItemRepository
                .existsByProductIdAndShopClientId(productId, clientId)){
            updateQuantity(productId, clientId);
        } else {
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, shopClient, 1);
            shopClient.setShoppingCart(Collections.singletonList(shoppingCartItem));
            shoppingCartItemRepository.save(shoppingCartItem);
        }
    }

    @Transactional
    public void updateQuantity(UpdateQuantityDTO dto) {
        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository
                .findByProductIdAndClientId(dto.getProductId(), dto.getClientId());
        shoppingCartItem.setQuantity(dto.getQuantity());
        shoppingCartItemRepository.save(shoppingCartItem);
    }

    @Transactional
    public void updateQuantity(long productId, long clientId) {
        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository
                .findByProductIdAndClientId(productId, clientId);
        int quantity = shoppingCartItem.getQuantity();
        shoppingCartItem.setQuantity(++quantity);
        shoppingCartItemRepository.save(shoppingCartItem);
    }

    @Transactional
    public void deleteFromCart(CartItemsDTO dto) {
        shoppingCartItemRepository.deleteByProductIdAndClientId(dto.getProductId(), dto.getClientId());
    }

    @Transactional
    public void deleteAllFromCart(long id){
        shoppingCartItemRepository.deleteAllByShopClientId(id);
    }
}
