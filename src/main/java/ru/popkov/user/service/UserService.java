package ru.popkov.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.popkov.user.entity.User;
import ru.popkov.user.entity.UserDTO;
import ru.popkov.user.exception.UserNotFoundException;
import ru.popkov.user.repository.UserRepository;
import ru.popkov.user.util.UserMapper;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO getUserDTO(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return userMapper.toDTO(user.get());
        } else {
            throw new UserNotFoundException();
        }
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(UUID id, User newUser) {
        Optional<User> oldUser = userRepository.findById(id);
        if (oldUser.isPresent()) {
            newUser.setId(oldUser.get().getId());
            userRepository.save(newUser);
        } else {
            throw new UserNotFoundException();
        }
    }

    public void deleteUser(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException();
        }
    }

}
