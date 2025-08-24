package com.danilodev.ecommerce.backend.infraestructure.adapter;


import com.danilodev.ecommerce.backend.domain.model.OrderState;
import com.danilodev.ecommerce.backend.infraestructure.entity.OrderEntity;
import com.danilodev.ecommerce.backend.infraestructure.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IOrderCrudRepository extends CrudRepository<OrderEntity, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE OrderEntity o SET o.orderState=:state WHERE o.id=:id")
    void updateStateById(Integer id, OrderState state);

    Iterable<OrderEntity> findByUserEntity(UserEntity userEntity);
}
