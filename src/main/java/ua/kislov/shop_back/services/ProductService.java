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

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> catalog(int page, int size, String sort){
        PageRequest pageable =  PageRequest.of(page, size, Sort.by(sort));
        return productRepository.findAll(pageable);
    }

    public Product getProduct(long id) throws ProductNotFoundException{
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product is not found"));
    }

    public Product getProxy(long id){
        return productRepository.getReferenceById(id);
    }
}
