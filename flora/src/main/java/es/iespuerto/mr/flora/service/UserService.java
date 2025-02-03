package es.iespuerto.mr.flora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
@Component
public class UserService implements UserServiceInterface {

    private UserRepository userRepository;

    /**
     * Sets the {@link UserRepository} dependency for this service.
     *
     * @param userRepository the user repository to be injected.
     */
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a list of all users from the database.
     *
     * @return a list of all users.
     */
    @Override
    public List<User> getAllUsers() {
        log.info("Realizando la llamada al servicio");
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve.
     * @return the user with the given ID.
     * @throws ResourceNotFoundException if no user is found with the provided ID.
     */
    @Override
    public User getUserById(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
    }

    /**
     * Creates a new user in the database.
     *
     * @param user the user to create.
     * @return the created user.
     */
    @Override
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    /**
     * Updates an existing user with new details.
     *
     * @param userId the ID of the user to update.
     * @param userDetails the new details for the user.
     * @return the updated user.
     * @throws ResourceNotFoundException if no user is found with the provided ID.
     */
    @Override
    public User updateUser(@PathVariable(value = "id") int userId,
                           @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        user.setName(userDetails.getName());
        return userRepository.save(user);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to delete.
     * @throws ResourceNotFoundException if no user is found with the provided ID.
     */
    @Override
    public void deleteUser(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userRepository.delete(user);
    }

}
