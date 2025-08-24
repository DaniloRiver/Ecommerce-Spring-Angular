package com.danilodev.ecommerce.backend.infraestructure.rest;

import com.danilodev.ecommerce.backend.application.ProductService;
import com.danilodev.ecommerce.backend.domain.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/admin/products")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestParam("id" )Integer id,
                                        @RequestParam("name")String name,
                                        @RequestParam("code") String code,
                                        @RequestParam("description")String description,
                                        @RequestParam("price") BigDecimal price,
                                        @RequestParam("urlImage")String urlImage,
                                        @RequestParam("userId")Integer userId,
                                        @RequestParam("categoryId")Integer categoryId,
                                        @RequestParam(value="image", required = false)MultipartFile multipartFile

                                        ) throws IOException {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setCode(code);
        product.setDescription(description);
        product.setPrice(price);
        product.setUrlImage(urlImage);
        product.setUserId(userId);
        product.setCategoryId(categoryId);

        log.info("Nombre del producto: {}",product.getName());
        return new ResponseEntity<>(productService.save(product, multipartFile), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll(){
        return  ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id){
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
