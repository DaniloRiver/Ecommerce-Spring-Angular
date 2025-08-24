package com.danilodev.ecommerce.backend.infraestructure.mapper;

import com.danilodev.ecommerce.backend.domain.model.Order;
import com.danilodev.ecommerce.backend.infraestructure.entity.OrderEntity;
import com.danilodev.ecommerce.backend.infraestructure.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = OrderProductMapper.class)
public interface OrderMapper {
    @Mappings(
            {
                    @Mapping(source = "id", target = "id"),
                    @Mapping(source = "dateCreated", target = "dateCreated"),
                    @Mapping(source = "orderProducts", target = "orderProducts"),
                    @Mapping(source = "orderState", target = "orderState"),
                    @Mapping(source = "userEntity.id", target = "userId")
            }
    )
    Order toOrder(OrderEntity orderEntity);

    Iterable<Order> toOrderList(Iterable<OrderEntity> orderEntities);

    @InheritInverseConfiguration
    OrderEntity toOrderEntity(Order order);

    // Metodo auxiliar para mapear el userId a un UserEntity con solo el ID
    default UserEntity mapUserIdToUserEntity(Integer userId) {
        if (userId == null) return null;
        UserEntity user = new UserEntity();
        user.setId(userId);
        return user;

    }
}
