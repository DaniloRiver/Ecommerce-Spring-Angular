package com.danilodev.ecommerce.backend.infraestructure.adapter;

import com.danilodev.ecommerce.backend.infraestructure.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryCrudRepository extends CrudRepository<CategoryEntity, Integer> {
}
