package com.example.fabriquetesttask.mapper;

import com.example.fabriquetesttask.dto.UserDto;
import com.example.fabriquetesttask.model.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    @Named("userToUserDto")
    UserDto userToUserDto(User entity);
    User userDtoToUser(UserDto dto);
    @IterableMapping(qualifiedByName = "userToUserDto")
    List<UserDto> allToDto(List<User> users);
}
