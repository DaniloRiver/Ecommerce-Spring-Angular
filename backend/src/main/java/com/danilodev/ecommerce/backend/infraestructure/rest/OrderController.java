package com.danilodev.ecommerce.backend.infraestructure.rest;

import com.danilodev.ecommerce.backend.application.OrderService;
import com.danilodev.ecommerce.backend.domain.model.Order;
import com.danilodev.ecommerce.backend.domain.model.OrderState;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order){
        if(order.getOrderState().toString().equals(OrderState.CANCELLED.toString())){
            order.setOrderState(OrderState.CANCELLED);
        }else{
            order.setOrderState(OrderState.CONFIRMED);
        }
        return ResponseEntity.ok(orderService.save(order));
    }

    @PostMapping(value = "/update/state/order", consumes = "multipart/form-data" , produces = "application/json")
    public ResponseEntity<String> updateStateById(@RequestParam Integer id, @RequestParam String state) {
        if (id == null) {
            return ResponseEntity.badRequest().body("ID inválido o nulo");
        }

        try {
            orderService.updateStateById(id, state);
            return ResponseEntity.ok("Estado actualizado correctamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Orden no encontrada");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Estado inválido");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar estado");
        }
    }


    @GetMapping
    public ResponseEntity<Iterable<Order>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<Iterable<Order>> findByOrderByUser(@PathVariable Integer userId) {
        Iterable<Order> orders = orderService.findByUserId(userId);
        return ResponseEntity.ok(orders);
    }


}
