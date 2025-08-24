package com.danilodev.ecommerce.backend.infraestructure.rest;

import com.danilodev.ecommerce.backend.application.UserService;
import com.danilodev.ecommerce.backend.domain.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public User finById(@PathVariable Integer id){
        return userService.findById(id);
    }

}
