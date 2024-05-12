package ru.popkov.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.popkov.user.entity.CustomResponse;

import static ru.popkov.user.util.MessageUtil.USER_NOT_FOUND;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super(USER_NOT_FOUND);
    }

    public ResponseEntity<?> getNotFoundResponse() {
        CustomResponse customResponse = new CustomResponse(USER_NOT_FOUND, HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customResponse);
    }
}
