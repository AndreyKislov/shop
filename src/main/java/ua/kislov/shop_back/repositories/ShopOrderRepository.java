package ua.kislov.shop_back.repositories;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.kislov.shop_back.model.OrderStatus;
import ua.kislov.shop_back.model.ShopOrder;

import java.util.List;

@Repository
public interface ShopOrderRepository extends JpaRepository<ShopOrder, Long> {
    @Query("SELECT p FROM ShopOrder p WHERE p.orderStatus = :orderStatus")
    List<ShopOrder> findAllWhereStatusIsNew(OrderStatus orderStatus);

    @Modifying
    @Query("UPDATE ShopOrder p SET p.orderStatus = :orderStatus WHERE p.orderId = :id")
    void exchangeStatus(long id, OrderStatus orderStatus);
}
