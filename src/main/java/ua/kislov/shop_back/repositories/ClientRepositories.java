package ua.kislov.shop_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kislov.shop_back.model.ShopClient;

@Repository
public interface ClientRepositories extends JpaRepository<ShopClient, Long> {
    boolean existsByEmail(String email);
}
