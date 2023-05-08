package ua.kislov.shop_back.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ua.kislov.shop_back.model.Product;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {
    private Product product;
    private int quantity;
}
