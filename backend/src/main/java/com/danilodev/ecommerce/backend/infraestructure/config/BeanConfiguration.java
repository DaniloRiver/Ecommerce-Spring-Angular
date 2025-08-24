package com.danilodev.ecommerce.backend.infraestructure.config;

import com.danilodev.ecommerce.backend.application.*;
import com.danilodev.ecommerce.backend.domain.port.ICategoryRepository;
import com.danilodev.ecommerce.backend.domain.port.IOrderRepository;
import com.danilodev.ecommerce.backend.domain.port.IProductRepository;
import com.danilodev.ecommerce.backend.domain.port.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public UploadFile uploadFile() {
        return new UploadFile(); // o pasa parámetros si necesita
    }

    @Bean
    public UserService userService(IUserRepository iUserRepository){
        return new UserService(iUserRepository);
    }

    @Bean
    public CategoryService categoryService(ICategoryRepository iCategoryRepository){
        return new CategoryService(iCategoryRepository);
    }

    @Bean
    public ProductService productService(IProductRepository iProductRepository, UploadFile uploadFile){
        return new ProductService(iProductRepository, uploadFile);
    }

    @Bean
    public OrderService orderService(IOrderRepository iOrderRepository){
        return new OrderService(iOrderRepository);
    }

    @Bean
    public RegistrationService registrationService(IUserRepository iUserRepository){
        return new RegistrationService(iUserRepository);
    }

}
