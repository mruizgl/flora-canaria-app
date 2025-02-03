package es.iespuerto.mr.flora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.User;
import es.iespuerto.mr.flora.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class for managing users.
 * <p>This class implements the {@link UserServiceInterface} and provides methods to perform CRUD operations on users.</p>
 * <p>It uses {@link UserRepository} to interact with the database.</p>
 * 
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Slf4j} - For logging purposes, enabling the logging feature in the class.</li>
 *   <li>{@code @Component} - Indicates that this class is a Spring component, allowing Spring to manage it as a bean.</li>
 *   <li>{@code @Autowired} - Injects the {@link UserRepository} dependency into this service.</li>
 * </ul>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #getAllUsers()} - Retrieves a list of all users from the database.</li>
 *   <li>{@link #getUserById(int)} - Retrieves a user by their ID.</li>
 *   <li>{@link #createUser(User)} - Creates a new user.</li>
 *   <li>{@link #updateUser(int, User)} - Updates an existing user based on their ID.</li>
 *   <li>{@link #deleteUser(int)} - Deletes a user by their ID.</li>
 * </ul>
 * 
 * <p>Exceptions:</p>
 * <ul>
 *   <li>{@link ResourceNotFoundException} - Thrown when a user is not found for a given ID.</li>
 * </ul>
 * 
 * @see UserServiceInterface
 * @see UserRepository
 * @see User
 * @see ResourceNotFoundException
 */
@Slf4j
@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Obtener todos los usuarios
    public List<User> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    // Obtener un usuario por ID
    public User getUserById(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
        log.info("Fetching user with id: {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
    }

    // Crear un nuevo usuario
    public User createUser(@Valid @RequestBody User user) {
        log.info("Creating new user with name: {}", user.getName());
        return userRepository.save(user);
    }

    // Actualizar un usuario existente
    public User updateUser(@PathVariable(value = "id") int userId,
                           @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        log.info("Updating user with id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        // Actualizamos los detalles del usuario
        user.setName(userDetails.getName());
        // Aquí puedes agregar más campos si es necesario
        return userRepository.save(user);
    }

    public void deleteUser(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
        log.info("Deleting user with id: {}", userId);
    
        // Obtener el usuario a eliminar
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
    
        
    if (user.getRole() != null) {
        user.setRole(null);
        userRepository.save(user);  
    }
        userRepository.delete(user);
        log.info("User with id: {} deleted successfully", userId);
    }
    
}
