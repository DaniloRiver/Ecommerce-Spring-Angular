package com.danilodev.ecommerce.backend.application;

import com.danilodev.ecommerce.backend.domain.model.Category;
import com.danilodev.ecommerce.backend.domain.port.ICategoryRepository;

public class CategoryService {
    private final ICategoryRepository iCategoryRepository;

    public CategoryService(ICategoryRepository iCategoryRepository) {
        this.iCategoryRepository = iCategoryRepository;
    }

    public Category save(Category category){
        return iCategoryRepository.save(category);
    }

    public Iterable<Category> findAll(){
        return  iCategoryRepository.finAll();
    }

    public Category findById(Integer id){
        return iCategoryRepository.finById(id);
    }

    public void deleteById(Integer id){
        iCategoryRepository.deleteById(id);
    }

}
