package com.danilodev.ecommerce.backend.domain.port;

import com.danilodev.ecommerce.backend.domain.model.Product;

public interface IProductRepository {

    Product save(Product product);
    Iterable<Product> findAll();
    Product finById(Integer id);
    void deleteById(Integer id);
}
