package ru.popkov.user.entity;

import lombok.Getter;
import lombok.Setter;


public class UserDTO {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
