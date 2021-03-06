package com.Innoteq.innoteq.service;

import com.Innoteq.innoteq.model.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    List<Product> findAll();

    Product findById(Long id);
}
