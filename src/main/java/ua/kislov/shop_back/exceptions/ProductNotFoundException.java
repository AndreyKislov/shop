package ua.kislov.shop_back.exceptions;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException{
    private final String message = "Product is not found";
}
