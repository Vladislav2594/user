package ru.popkov.user.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomResponse {
    private String message;
    private int code;

    public CustomResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
