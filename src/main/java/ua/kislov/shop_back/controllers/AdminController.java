package ua.kislov.shop_back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kislov.shop_back.model.Product;
import ua.kislov.shop_back.model.ShopClient;
import ua.kislov.shop_back.services.interfaces.AdminServiceInterface;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminServiceInterface adminService;

    @Autowired
    public AdminController(AdminServiceInterface adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/client/{id}")
    ResponseEntity<ShopClient> shopClient(@PathVariable("id") long id){
        return new ResponseEntity<>(adminService.shopClient(id), HttpStatus.OK);
    }

    @DeleteMapping("/client/{id}")
    ResponseEntity<Void> deleteShopClient(@PathVariable("id") long id){
        adminService.deleteShopClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    ResponseEntity<Product> product(@PathVariable("id") long id){
        return new ResponseEntity<>(adminService.product(id), HttpStatus.OK);
    }

    @PostMapping("/new_product")
    ResponseEntity<Void> saveNewProduct(@RequestBody Product product){
        adminService.saveNewProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/product_isExists")
    ResponseEntity<Boolean> productIsExists(@RequestParam("name") String name){
        return new ResponseEntity<>(adminService.productIsExistsByName(name),
                HttpStatus.OK);
    }
}
