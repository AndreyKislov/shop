package ua.kislov.shop_back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.kislov.shop_back.dto.OrderDTO;
import ua.kislov.shop_back.services.interfaces.OrderInterface;

@RestController
public class OrderController {

    private final OrderInterface orderService;

    @Autowired
    public OrderController(OrderInterface orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/make-order")
    ResponseEntity<Void> makeOrder(@RequestParam("clientId")long id) {
        orderService.makeOrder(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
