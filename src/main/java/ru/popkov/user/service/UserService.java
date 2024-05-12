package ru.popkov.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.popkov.user.entity.User;
import ru.popkov.user.entity.UserDTO;
import ru.popkov.user.entity.UserUpdateRq;
import ru.popkov.user.exception.UserNotFoundException;
import ru.popkov.user.repository.UserRepository;
import ru.popkov.user.mapper.UserMapper;

import java.util.Optional;
import java.util.UUID;

import static ru.popkov.user.util.MessageUtil.USER_CREATE_SUCCESS;
import static ru.popkov.user.util.MessageUtil.USER_DELETE_ERROR;
import static ru.popkov.user.util.MessageUtil.USER_DELETE_SUCCESS;
import static ru.popkov.user.util.MessageUtil.USER_GET_SUCCESS;
import static ru.popkov.user.util.MessageUtil.USER_NOT_FOUND;
import static ru.popkov.user.util.MessageUtil.USER_UPDATE_SUCCESS;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserDTO getUser(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            createLog(USER_GET_SUCCESS, id);
            return userMapper.toDTO(user.get());
        } else {
            createLog(USER_NOT_FOUND, id);
            throw new UserNotFoundException();
        }
    }

    public void createUser(User user) {
        userRepository.save(user);
        createLog(USER_CREATE_SUCCESS, user.getId());
    }

    public void updateUser(UUID id, UserUpdateRq newUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        User oldUser = optionalUser.orElseThrow(UserNotFoundException::new);

        oldUser.setUsername(newUser.getUsername());
        oldUser.setPhone(newUser.getPhone());
        oldUser.setEmail(newUser.getEmail());
        userRepository.save(oldUser);
        createLog(USER_UPDATE_SUCCESS, id);
    }

    public void deleteUser(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            createLog(USER_DELETE_SUCCESS, id);
        } else {
            createLog(USER_DELETE_ERROR, id);
            throw new UserNotFoundException();
        }
    }

    public void createLog(String message, UUID id) {
        log.info(message + " ID: " + id);
    }

    public void createLog(String message) {
        log.info(message);
    }

}
