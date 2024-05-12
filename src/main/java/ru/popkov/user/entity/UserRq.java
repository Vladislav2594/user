package ru.popkov.user.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
public class UserRq {

    private String username;
    private String phone;
    private String email;

}
