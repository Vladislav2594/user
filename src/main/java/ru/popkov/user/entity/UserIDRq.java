package ru.popkov.user.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Setter
@Getter
public class UserIDRq {

    @NotNull(message = "Пустой ID!")
    private UUID id;

}
