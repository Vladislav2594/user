package ru.popkov.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.popkov.user.entity.CustomResponse;
import ru.popkov.user.entity.UserIDRq;
import ru.popkov.user.entity.UserRq;
import ru.popkov.user.entity.UserUpdateRq;
import ru.popkov.user.exception.UserNotFoundException;
import ru.popkov.user.service.UserService;
import ru.popkov.user.mapper.UserMapper;

import java.util.UUID;

import static ru.popkov.user.util.MessageUtil.USER_CREATE_ERROR;
import static ru.popkov.user.util.MessageUtil.USER_CREATE_SUCCESS;
import static ru.popkov.user.util.MessageUtil.USER_DELETE_ERROR;
import static ru.popkov.user.util.MessageUtil.USER_DELETE_SUCCESS;
import static ru.popkov.user.util.MessageUtil.USER_UPDATE_ERROR;
import static ru.popkov.user.util.MessageUtil.USER_UPDATE_SUCCESS;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@Validated
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping("/get")
    public ResponseEntity<?> getUser(@RequestParam(name = "id") UUID id) {
        try {
            return ResponseEntity.ok(userService.getUser(id));
        } catch (UserNotFoundException e) {
            return e.getNotFoundResponse();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRq userRq, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            userService.createLog(USER_CREATE_ERROR);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse(USER_CREATE_ERROR, HttpStatus.BAD_REQUEST.value()));
        } else {
            userService.createUser(userMapper.toUser(userRq));
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(USER_CREATE_SUCCESS, HttpStatus.OK.value()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateRq userUpdateRq, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            userService.createLog(USER_UPDATE_ERROR, userUpdateRq.getId());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse(USER_UPDATE_ERROR, HttpStatus.BAD_REQUEST.value()));
        } else {
            try {
                userService.updateUser(userUpdateRq.getId(), userUpdateRq);
                return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(USER_UPDATE_SUCCESS, HttpStatus.OK.value()));
            } catch (UserNotFoundException e) {
                return e.getNotFoundResponse();
            }
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody UserIDRq userIDRq, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            userService.createLog(USER_DELETE_ERROR, userIDRq.getId());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse(USER_DELETE_ERROR, HttpStatus.BAD_REQUEST.value()));
        } else {
            try {
                userService.deleteUser(userIDRq.getId());
                return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(USER_DELETE_SUCCESS, HttpStatus.OK.value()));
            } catch (UserNotFoundException e) {
                return e.getNotFoundResponse();
            }
        }
    }

}
