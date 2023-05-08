package ua.kislov.shop_back.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartItemsDTO {
    private long productId;
    private long clientId;
}
