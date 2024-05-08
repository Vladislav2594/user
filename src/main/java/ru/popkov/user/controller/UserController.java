package ru.popkov.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.popkov.user.entity.User;
import ru.popkov.user.entity.UserDTO;
import ru.popkov.user.exception.UserNotFoundException;
import ru.popkov.user.service.UserService;

import java.util.UUID;

import static ru.popkov.user.util.MessageUtil.SUCCESS;
import static ru.popkov.user.util.MessageUtil.USER_NOT_FOUND;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable UUID id) {
        return userService.getUserDTO(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestParam(name = "name") String username,
                              @RequestParam(name = "phone", required = false) String phone,
                              @RequestParam(name = "email", required = false) String email) {
        User user = new User();
        user.setUsername(username);
        user.setPhone(phone);
        user.setEmail(email);
        userService.createUser(user);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@RequestParam(name = "id") UUID id,
                              @RequestParam(name = "name", required = false) String username,
                              @RequestParam(name = "phone", required = false) String phone,
                              @RequestParam(name = "email", required = false) String email) {
        User user = new User(username, phone, email);
        try {
            userService.updateUser(id, user);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(USER_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam(name = "id") UUID id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(USER_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
    }

}
