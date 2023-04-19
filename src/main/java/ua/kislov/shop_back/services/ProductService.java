package ua.kislov.shop_back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kislov.shop_back.exceptions.ProductNotFoundException;
import ua.kislov.shop_back.model.Product;
import ua.kislov.shop_back.repositories.ProductRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> catalog(int page, int size, String sort){
        PageRequest pageable =  PageRequest.of(page, size, Sort.by(sort));
        Page<Product> products = repository.findAll(pageable);
        return products.getContent();
    }

    public Product product(long id) throws ProductNotFoundException{
        return repository.findById(id).orElseThrow(ProductNotFoundException::new);
    }
}
