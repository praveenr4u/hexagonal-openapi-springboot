package com.launch.template.infrastructure.data.mapper;


import com.launch.template.domain.entity.UserEntity;
import com.launch.template.infrastructure.data.dto.UserDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    @BeanMapping(builder = @Builder( disableBuilder = true ))
    UserDto userToUserDto(UserEntity userEntity);
    @BeanMapping(builder = @Builder( disableBuilder = true ))
    UserEntity userDtoToUser(UserDto userDto);
    List<UserEntity> userDtoListToUserList(List<UserDto> all);
}
