package es.iespuerto.mr.flora.soap.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.User;
import es.iespuerto.mr.flora.service.UserServiceInterface;
import jakarta.xml.ws.WebServiceException;

public class UserServiceSoapImplTest {

    @Mock
    private UserServiceInterface userService;

    @InjectMocks
    private UserServiceSoapImpl userServiceSoap;

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

        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userServiceSoap.getAllUsers();

        assertEquals(2, users.size());
        assertEquals(1, users.get(0).getId());
        assertEquals(2, users.get(1).getId());
    }

    @Test
    void getAllUsersThrowsWebServiceExceptionOnError() {
        when(userService.getAllUsers()).thenThrow(new RuntimeException("Error"));

        assertThrows(WebServiceException.class, () -> {
            userServiceSoap.getAllUsers();
        });
    }

    @Test
    void getUserByIdReturnsUserIfExists() throws ResourceNotFoundException {
        User user = new User();
        user.setId(1);

        when(userService.getUserById(1)).thenReturn(user);

        User foundUser = userServiceSoap.getUserById(1);

        assertEquals(1, foundUser.getId());
    }

    @Test
    void getUserByIdThrowsWebServiceExceptionIfUserNotFound() throws ResourceNotFoundException {
        when(userService.getUserById(1)).thenThrow(new ResourceNotFoundException("User not found"));

        assertThrows(WebServiceException.class, () -> {
            userServiceSoap.getUserById(1);
        });
    }

    @Test
    void createUserSavesAndReturnsUser() {
        User user = new User();
        user.setId(1);

        when(userService.createUser(user)).thenReturn(user);

        User createdUser = userServiceSoap.createUser(user);

        assertEquals(1, createdUser.getId());
    }

    @Test
    void createUserThrowsWebServiceExceptionOnError() {
        User user = new User();
        user.setId(1);

        when(userService.createUser(user)).thenThrow(new RuntimeException("Error"));

        assertThrows(WebServiceException.class, () -> {
            userServiceSoap.createUser(user);
        });
    }

    @Test
    void updateUserUpdatesAndReturnsUser() throws ResourceNotFoundException {
        User existingUser = new User();
        existingUser.setId(1);

        User updatedDetails = new User();
        updatedDetails.setId(1);

        when(userService.updateUser(1, updatedDetails)).thenReturn(existingUser);

        User updatedUser = userServiceSoap.updateUser(1, updatedDetails);

        assertEquals(1, updatedUser.getId());
    }

    @Test
    void updateUserThrowsWebServiceExceptionIfUserNotFound() throws ResourceNotFoundException {
        User updatedDetails = new User();
        updatedDetails.setId(1);

        when(userService.updateUser(1, updatedDetails)).thenThrow(new ResourceNotFoundException("User not found"));

        assertThrows(WebServiceException.class, () -> {
            userServiceSoap.updateUser(1, updatedDetails);
        });
    }

    @Test
    void deleteUserDeletesUserIfExists() throws ResourceNotFoundException {
        doNothing().when(userService).deleteUser(1);

        userServiceSoap.deleteUser(1);

        verify(userService, times(1)).deleteUser(1);
    }

    @Test
    void deleteUserThrowsWebServiceExceptionIfUserNotFound() throws ResourceNotFoundException {
        doThrow(new ResourceNotFoundException("User not found")).when(userService).deleteUser(1);

        assertThrows(WebServiceException.class, () -> {
            userServiceSoap.deleteUser(1);
        });
    }
}