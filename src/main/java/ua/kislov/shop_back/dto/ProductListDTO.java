package ua.kislov.shop_back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.kislov.shop_back.model.Product;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductListDTO {
    private List<Product> productList;
    private int totalPage;
}
