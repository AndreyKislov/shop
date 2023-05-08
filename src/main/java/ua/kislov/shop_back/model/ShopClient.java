package ua.kislov.shop_back.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class ShopClient {
    @Id
    @Column(name = "client_id")
    private long id;
    @Column(name = "client_name")
    private String clientName;
    @Column(name = "client_surname")
    private String surname;
    @Column(name = "client_email")
    private String email;
    @Column(name = "client_number")
    private String number;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shopClient")
    private List<ShoppingCartItem> shoppingCart;
}
