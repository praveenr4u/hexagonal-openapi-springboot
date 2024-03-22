package com.launch.template.service.mapper;

import com.launch.template.domain.entity.UserEntity;
import com.launch.template.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userFromDomainToResponse(UserEntity entity);

    List<User> usersFromDomainToResponse(List<UserEntity> entities);
}
