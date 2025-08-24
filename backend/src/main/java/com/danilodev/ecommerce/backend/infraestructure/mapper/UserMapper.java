package com.danilodev.ecommerce.backend.infraestructure.mapper;

import com.danilodev.ecommerce.backend.domain.model.User;
import com.danilodev.ecommerce.backend.infraestructure.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings(
            {
                    @Mapping(source ="id", target = "id"),
                    @Mapping(source = "username", target = "username"),
                    @Mapping(source = "firstname", target = "firstname"),
                    @Mapping(source = "lastname", target = "lastname"),
                    @Mapping(source = "email", target = "email"),
                    @Mapping(source = "address", target = "address"),
                    @Mapping(source = "cellphone", target = "cellphone"),
                    @Mapping(source = "password", target = "password"),
                    @Mapping(source = "userType", target = "userType"),
                    @Mapping(source = "dataCreated", target = "dataCreated"),
                    @Mapping(source = "dataUpdated", target = "dataUpdated")
            }
    )

    User toUser(UserEntity userEntity);
    Iterable<User> toUsers(Iterable<UserEntity> userEntities);

    @InheritInverseConfiguration
    UserEntity toUserEntity(User user);



}
