package ru.popkov.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.popkov.user.entity.User;
import ru.popkov.user.entity.UserDTO;
import ru.popkov.user.mapper.UserMapper;
import ru.popkov.user.repository.UserRepository;
import ru.popkov.user.service.UserService;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUser() {

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername("TestName");
        user.setEmail("testmymail.ru");
        user.setPhone("+79995556262");

        Mockito.when(userService.getUser(user.getId())).thenReturn(userMapper.toDTO(user));
        String username = user.getUsername();

        Assertions.assertEquals("TestName", username);
    }
}
