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
 *   <li>{@code @Service} - Marks this class as a Spring service, allowing Spring to manage it as a bean.</li>
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

    /**
     * Constructor that initializes the {@link UserRepository}.
     * 
     * @param userRepository The {@link UserRepository} to be injected.
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all users from the database.
     * 
     * @return A list of all {@link User} objects.
     */
    public List<User> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their ID.
     * 
     * @param userId The ID of the user to be fetched.
     * @return The {@link User} with the specified ID.
     * @throws ResourceNotFoundException If no user is found for the given ID.
     */
    public User getUserById(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
        log.info("Fetching user with id: {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
    }

    /**
     * Creates a new user.
     * 
     * @param user The {@link User} object to be created.
     * @return The created {@link User}.
     */
    public User createUser(@Valid @RequestBody User user) {
        log.info("Creating new user with name: {}", user.getName());
        return userRepository.save(user);
    }

    /**
     * Updates an existing user based on their ID.
     * 
     * @param userId The ID of the user to be updated.
     * @param userDetails The new {@link User} details.
     * @return The updated {@link User}.
     * @throws ResourceNotFoundException If no user is found for the given ID.
     */
    public User updateUser(@PathVariable(value = "id") int userId,
                           @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        log.info("Updating user with id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        // Update user details
        user.setName(userDetails.getName());
        // Add more fields if necessary
        return userRepository.save(user);
    }

    /**
     * Deletes a user by their ID.
     * 
     * @param userId The ID of the user to be deleted.
     * @throws ResourceNotFoundException If no user is found for the given ID.
     */
    public void deleteUser(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
        log.info("Deleting user with id: {}", userId);
    
        // Find the user to delete
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
    
        // Remove the role association if necessary
        if (user.getRole() != null) {
            user.setRole(null);
            userRepository.save(user);  
        }

        // Delete the user
        userRepository.delete(user);
        log.info("User with id: {} deleted successfully", userId);
    }
}
