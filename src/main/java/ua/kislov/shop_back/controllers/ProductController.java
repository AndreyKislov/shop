package ua.kislov.shop_back.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kislov.shop_back.dto.CartItemsDTO;
import ua.kislov.shop_back.dto.ProductDTO;
import ua.kislov.shop_back.dto.ProductListDTO;
import ua.kislov.shop_back.dto.UpdateQuantityDTO;
import ua.kislov.shop_back.model.Product;
import ua.kislov.shop_back.services.CartService;
import ua.kislov.shop_back.services.ClientService;
import ua.kislov.shop_back.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ProductController {
    private final ProductService productService;
    private final ClientService clientService;
    private final CartService cartService;

    @Autowired
    public ProductController(ProductService productService,
                             ClientService clientService,
                             CartService cartService) {
        this.productService = productService;
        this.clientService = clientService;
        this.cartService = cartService;
    }

    @GetMapping("/catalog")
    public ResponseEntity<ProductListDTO> catalog(@RequestParam(value = "page", required = false) int page,
                                                  @RequestParam(value = "size", required = false) int size,
                                                  @RequestParam(value = "sortBy", required = false) String sortBy) {
        Page<Product> products = productService.catalog(page, size, sortBy);
        ProductListDTO dto = new ProductListDTO(products.getContent(), products.getTotalPages());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("catalog/{id}")
    public ResponseEntity<Product> product(@PathVariable("id") long id){
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @GetMapping("/client/get-cart")
    ResponseEntity<List<ProductDTO>> getShopCart(@RequestParam("id") long id){
        List<ProductDTO> cart = cartService.getCart(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/catalog/addToCart")
    ResponseEntity<Void> addToCart(@RequestBody CartItemsDTO dto){
        cartService.addToCart(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/catalog/updateQuantity")
    ResponseEntity<Void> updateQuantity(@RequestBody UpdateQuantityDTO dto){
          cartService.updateQuantity(dto);
          return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/catalog/deleteFromCart")
    ResponseEntity<Void> deleteFromCart(@RequestBody CartItemsDTO dto){
        cartService.deleteFromCart(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
