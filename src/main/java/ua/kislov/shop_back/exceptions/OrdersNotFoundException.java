package ua.kislov.shop_back.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrdersNotFoundException extends RuntimeException{
    private String message;
}
