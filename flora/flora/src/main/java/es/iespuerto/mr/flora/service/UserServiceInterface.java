package es.iespuerto.mr.flora.service;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> getAllUsers();
    User getUserById(int userId) throws ResourceNotFoundException;
    public User createUser(User user);
    User updateUser(int userId, User userDetails)       throws ResourceNotFoundException;
    void deleteUser(int userId) throws ResourceNotFoundException;
}

