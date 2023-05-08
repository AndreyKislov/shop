package ua.kislov.shop_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.kislov.shop_back.model.ShoppingCartItem;

import java.util.List;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

    @Query("SELECT p FROM ShoppingCartItem p WHERE p.product.id = :productId AND p.shopClient.id = :clientId")
    ShoppingCartItem findByProductIdAndClientId(Long productId, Long clientId);

    List<ShoppingCartItem> findAllByShopClientId(long clientId);

    @Modifying
    @Query("DELETE  FROM ShoppingCartItem p WHERE p.product.id = :productId  AND p.shopClient.id = :clientId")
    void deleteByProductIdAndClientId(Long productId, Long clientId);

    boolean existsByProductIdAndShopClientId(long productId, long ClientId);

    @Modifying
    @Query("DELETE FROM ShoppingCartItem p WHERE p.shopClient.id = :id")
    void deleteAllByShopClientId(long id);

}
