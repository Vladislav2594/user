package ru.popkov.user.exception;

import static ru.popkov.user.util.MessageUtil.USER_NOT_FOUND;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super(USER_NOT_FOUND);
    }

}
