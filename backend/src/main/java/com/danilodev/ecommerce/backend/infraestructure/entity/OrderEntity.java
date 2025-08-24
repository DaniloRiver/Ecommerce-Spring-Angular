package com.danilodev.ecommerce.backend.infraestructure.entity;

import com.danilodev.ecommerce.backend.domain.model.OrderState;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Enumerated(value=EnumType.STRING)
    private OrderState orderState;

    @ManyToOne
    private UserEntity userEntity;

    @OneToMany(mappedBy = "orderEntity", cascade= CascadeType.PERSIST)
    private List<OrderProductEntity> orderProducts;
}
