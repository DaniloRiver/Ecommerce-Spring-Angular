package com.danilodev.ecommerce.backend.infraestructure.adapter;

import com.danilodev.ecommerce.backend.domain.model.Order;
import com.danilodev.ecommerce.backend.domain.model.OrderState;
import com.danilodev.ecommerce.backend.domain.port.IOrderRepository;
import com.danilodev.ecommerce.backend.infraestructure.entity.OrderEntity;
import com.danilodev.ecommerce.backend.infraestructure.entity.UserEntity;
import com.danilodev.ecommerce.backend.infraestructure.mapper.OrderMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OrderCrudRepositoryImpl implements IOrderRepository {

    private final IOrderCrudRepository iOrderCrudRepository;
    private OrderMapper orderMapper;

    public OrderCrudRepositoryImpl(IOrderCrudRepository iOrderCrudRepository, OrderMapper orderMapper) {
        this.iOrderCrudRepository = iOrderCrudRepository;
        this.orderMapper = orderMapper;
    }


    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = orderMapper.toOrderEntity(order);
        orderEntity.getOrderProducts().forEach(
                orderProductEntity -> orderProductEntity.setOrderEntity(orderEntity)
        );
        return orderMapper.toOrder(iOrderCrudRepository.save(orderEntity));
    }

    @Override
    public Order findById(Integer id) {
        return orderMapper.toOrder(iOrderCrudRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Order con id: "+id+"no encontrado.")
        ));
    }

    @Override
    public Iterable<Order> findAll() {
        return orderMapper.toOrderList(iOrderCrudRepository.findAll());
    }

    @Override
    public Iterable<Order> findByUserId(Integer userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        return orderMapper.toOrderList(iOrderCrudRepository.findByUserEntity(userEntity));
    }

    @Override
    public void updateStateById(Integer id, String state) {
        if(state.equals(OrderState.CANCELLED)){
            iOrderCrudRepository.updateStateById(id,OrderState.CANCELLED);
        }else{
            iOrderCrudRepository.updateStateById(id, OrderState.CONFIRMED);
        }
    }
}
