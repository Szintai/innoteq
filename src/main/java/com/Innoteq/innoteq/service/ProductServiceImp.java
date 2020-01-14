package com.Innoteq.innoteq.service;

import com.Innoteq.innoteq.model.Product;
import com.Innoteq.innoteq.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }


}
