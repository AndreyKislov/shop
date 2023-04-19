package ua.kislov.shop_back.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kislov.shop_back.model.Product;
import ua.kislov.shop_back.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> catalog(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                 @RequestParam(value = "size", required = false,
                                                     defaultValue = "10") int size,
                                                 @RequestParam(value = "sortBy", required = false,
                                                     defaultValue = "name") String sortBy) {
        return new ResponseEntity<>(productService.catalog(page, size, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> product(@PathVariable("id") long id){
        return new ResponseEntity<>(productService.product(id), HttpStatus.OK);
    }

}
