package com.danilodev.ecommerce.backend.infraestructure.adapter;

import com.danilodev.ecommerce.backend.infraestructure.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface IProductCrudRepository extends CrudRepository<ProductEntity, Integer> {
}
