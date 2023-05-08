package ua.kislov.shop_back.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kislov.shop_back.dto.ShopClientDTO;
import ua.kislov.shop_back.model.ShopClient;
import ua.kislov.shop_back.services.ClientService;


@RestController
@RequestMapping("client")
public class CreationController {

    private final ClientService services;
    private final ModelMapper mapper;

    @Autowired
    public CreationController(ClientService services, ModelMapper mapper) {
        this.services = services;
        this.mapper = mapper;
    }

    @GetMapping("/exists-id")
    public ResponseEntity<Boolean> isExistsById(@RequestParam("id") long id) {
        return new ResponseEntity<>(services.isExistsById(id), HttpStatus.OK);
    }

    @GetMapping("/exists-email")
    ResponseEntity<Boolean> existsByEmail(@RequestParam("email") String email) {
        return new ResponseEntity<>(services.isExistsByEmail(email), HttpStatus.OK);
    }

    @PostMapping("/additionalInformation")
    public ResponseEntity<Void> createShopCart(@RequestBody ShopClientDTO clientDTO) {
        ShopClient shopClient = toModel(clientDTO);
        services.save(shopClient);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private ShopClient toModel(ShopClientDTO dto) {
        return mapper.map(dto, ShopClient.class);
    }
}
