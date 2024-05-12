package ru.popkov.user.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Setter
@Getter
public class UserUpdateRq {

    @NotNull(message = "Пустой ID!")
    private UUID id;

    @NotNull(message = "Пожалуйста, укажите имя пользователя!")
    private String username;

    private String phone;

    @NotNull(message = "Пожалуйста, укажите почту!")
    private String email;

}
