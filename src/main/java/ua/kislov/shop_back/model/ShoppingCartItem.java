package ua.kislov.shop_back.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shopping_cart_item")
@ToString
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "client_id")
    private ShopClient shopClient;

    @Column(name = "cart_item_quantity")
    private int quantity;

    public ShoppingCartItem(Product product, ShopClient shopClient, int quantity) {
        this.product = product;
        this.shopClient = shopClient;
        this.quantity = quantity;
    }
}
