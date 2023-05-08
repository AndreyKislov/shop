package ua.kislov.shop_back.services.interfaces;

import ua.kislov.shop_back.dto.OrderDTO;
import ua.kislov.shop_back.dto.ProductDTO;

import java.util.List;

public interface OrderInterface {
    void makeOrder(long id);
}
