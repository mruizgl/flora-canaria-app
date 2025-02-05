package es.iespuerto.mr.flora.controller;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.User;
import es.iespuerto.mr.flora.service.UserServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsersControllerTest {

    @Mock
    private UserServiceInterface userService;

    @InjectMocks
    private UsersController usersController;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setName("Test User");
    }

    @Test
    void getAllUsersReturnsListOfUsers() {
        when(userService.getAllUsers()).thenReturn(List.of(user));

        List<User> users = usersController.getAllUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("Test User", users.get(0).getName());
    }

    @Test
    void getUserByIdReturnsUser() throws ResourceNotFoundException {
        when(userService.getUserById(1)).thenReturn(user);

        ResponseEntity<User> response = usersController.getUserById(1);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test User", response.getBody().getName());
    }

    @Test
    void getUserByIdThrowsResourceNotFoundException() throws ResourceNotFoundException {
        when(userService.getUserById(1)).thenThrow(new ResourceNotFoundException("User not found"));

        assertThrows(ResourceNotFoundException.class, () -> usersController.getUserById(1));
    }

    @Test
    void createUserReturnsCreatedUser() {
        when(userService.createUser(any(User.class))).thenReturn(user);

        User createdUser = usersController.createUser(user);

        assertNotNull(createdUser);
        assertEquals("Test User", createdUser.getName());
    }

    @Test
    void updateUserReturnsUpdatedUser() throws ResourceNotFoundException {
        when(userService.updateUser(eq(1), any(User.class))).thenReturn(user);

        ResponseEntity<User> response = usersController.updateUser(1, user);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test User", response.getBody().getName());
    }

    @Test
    void updateUserThrowsResourceNotFoundException() throws ResourceNotFoundException {
        when(userService.updateUser(eq(1), any(User.class))).thenThrow(new ResourceNotFoundException("User not found"));

        assertThrows(ResourceNotFoundException.class, () -> usersController.updateUser(1, user));
    }

    @Test
    void deleteUserReturnsSuccessMap() throws ResourceNotFoundException {
        doNothing().when(userService).deleteUser(1);

        Map<String, Boolean> response = usersController.deleteUser(1);

        assertTrue(response.get("deleted"));
    }

    @Test
    void deleteUserThrowsResourceNotFoundException() throws ResourceNotFoundException {
        doThrow(new ResourceNotFoundException("User not found")).when(userService).deleteUser(1);

        assertThrows(ResourceNotFoundException.class, () -> usersController.deleteUser(1));
    }
}