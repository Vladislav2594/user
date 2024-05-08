package ru.popkov.user.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.popkov.user.entity.User;
import ru.popkov.user.entity.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "username", target = "username")
    UserDTO toDTO(User user);

}
