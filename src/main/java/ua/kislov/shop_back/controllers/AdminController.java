package ua.kislov.shop_back.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kislov.shop_back.dto.OrdersDTO;
import ua.kislov.shop_back.model.Product;
import ua.kislov.shop_back.model.ShopClient;
import ua.kislov.shop_back.model.ShopOrder;
import ua.kislov.shop_back.services.interfaces.AdminServiceInterface;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminServiceInterface adminService;
    private final ObjectMapper objectMapper;

    @Autowired
    public AdminController(AdminServiceInterface adminService, ObjectMapper objectMapper) {
        this.adminService = adminService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/client/{id}")
    ResponseEntity<ShopClient> shopClient(@PathVariable("id") long id) {
        return new ResponseEntity<>(adminService.shopClient(id), HttpStatus.OK);
    }

    @DeleteMapping("/client/{id}")
    ResponseEntity<Void> deleteShopClient(@PathVariable("id") long id) {
        adminService.deleteShopClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    ResponseEntity<Product> product(@PathVariable("id") long id) {
        return new ResponseEntity<>(adminService.product(id), HttpStatus.OK);
    }

    @PostMapping("/new_product")
    ResponseEntity<Void> saveNewProduct(@RequestBody Product product) {
        adminService.saveNewProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/product_isExists")
    ResponseEntity<Boolean> productIsExists(@RequestParam("name") String name) {
        return new ResponseEntity<>(adminService.productIsExistsByName(name),
                HttpStatus.OK);
    }

    @GetMapping("/orders")
    ResponseEntity<String> getOrders() throws JsonProcessingException {
        List<ShopOrder> orders = adminService.getOrders();
        OrdersDTO ordersDTO = new OrdersDTO(orders);
        String s = objectMapper.writeValueAsString(ordersDTO);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping("/order")
    ResponseEntity<ShopOrder> getOrder(@RequestParam("id") long id) {
        ShopOrder order = adminService.getOrder(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/order")
    ResponseEntity<Void> completedOrder(@RequestParam("id") long id){
        adminService.completedOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
