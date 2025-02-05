package es.iespuerto.mr.flora.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.User;
import es.iespuerto.mr.flora.repository.UserRepository;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsersReturnsAllUsers() {
        User user1 = new User();
        user1.setId(1);
        User user2 = new User();
        user2.setId(2);

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getAllUsers();

        assertEquals(2, users.size());
        assertEquals(1, users.get(0).getId());
        assertEquals(2, users.get(1).getId());
    }

    @Test
    void getAllUsersThrowsExceptionOnError() {
        when(userRepository.findAll()).thenThrow(new RuntimeException("Error"));

        assertThrows(RuntimeException.class, () -> {
            userService.getAllUsers();
        });
    }

    @Test
    void getUserByIdReturnsUserIfExists() throws ResourceNotFoundException {
        User user = new User();
        user.setId(1);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1);

        assertEquals(1, foundUser.getId());
    }

    @Test
    void getUserByIdThrowsResourceNotFoundExceptionIfUserNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userService.getUserById(1);
        });
    }

    @Test
    void createUserSavesAndReturnsUser() {
        User user = new User();
        user.setId(1);

        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertEquals(1, createdUser.getId());
    }

    @Test
    void createUserThrowsExceptionOnError() {
        User user = new User();
        user.setId(1);

        when(userRepository.save(user)).thenThrow(new RuntimeException("Error"));

        assertThrows(RuntimeException.class, () -> {
            userService.createUser(user);
        });
    }

    @Test
    void updateUserUpdatesAndReturnsUser() throws ResourceNotFoundException {
        User existingUser = new User();
        existingUser.setId(1);

        User updatedDetails = new User();
        updatedDetails.setId(1);

        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User updatedUser = userService.updateUser(1, updatedDetails);

        assertEquals(1, updatedUser.getId());
    }

    @Test
    void updateUserThrowsResourceNotFoundExceptionIfUserNotFound() {
        User updatedDetails = new User();
        updatedDetails.setId(1);

        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userService.updateUser(1, updatedDetails);
        });
    }

    @Test
    void deleteUserDeletesUserIfExists() throws ResourceNotFoundException {
        User user = new User();
        user.setId(1);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        userService.deleteUser(1);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void deleteUserThrowsResourceNotFoundExceptionIfUserNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userService.deleteUser(1);
        });
    }
}