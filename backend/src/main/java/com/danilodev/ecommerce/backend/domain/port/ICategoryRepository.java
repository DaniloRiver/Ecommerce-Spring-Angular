package com.danilodev.ecommerce.backend.domain.port;

import com.danilodev.ecommerce.backend.domain.model.Category;

public interface ICategoryRepository {
    Category save(Category category);
    Iterable<Category> finAll();
    Category finById(Integer id);
    void deleteById(Integer id);
}
