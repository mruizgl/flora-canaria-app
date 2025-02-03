package es.iespuerto.mr.flora.service;

import java.util.List;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.User;

/**
 * Interface for user service operations.
 * <p>Provides methods to perform CRUD (Create, Read, Update, Delete) operations on User entities.</p>
 * 
 * <p>This interface defines the essential operations for managing {@link User} objects in the system.</p>
 * 
 * <p>Each method in this interface throws {@link ResourceNotFoundException} when a requested resource is not found.</p>
 * 
 * Methods:
 * <ul>
 *   <li>{@link #getAllUsers()}: Retrieves a list of all users.</li>
 *   <li>{@link #getUserById(int)}: Retrieves a user by their ID.</li>
 *   <li>{@link #createUser(User)}: Creates a new user.</li>
 *   <li>{@link #updateUser(int, User)}: Updates an existing user with new details.</li>
 *   <li>{@link #deleteUser(int)}: Deletes a user by their ID.</li>
 * </ul>
 * 
 * @see es.iespuerto.mr.flora.model.User
 * @see es.iespuerto.mr.flora.exception.ResourceNotFoundException
 */
public interface UserServiceInterface {

    /**
     * Retrieves a list of all users.
     *
     * @return a list of all users.
     */
    List<User> getAllUsers();

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve.
     * @return the user with the given ID.
     * @throws ResourceNotFoundException if no user is found with the provided ID.
     */
    User getUserById(int userId) throws ResourceNotFoundException;

    /**
     * Creates a new user.
     *
     * @param user the user to create.
     * @return the created user.
     */
    User createUser(User user);

    /**
     * Updates an existing user with new details.
     *
     * @param userId the ID of the user to update.
     * @param userDetails the updated details for the user.
     * @return the updated user.
     * @throws ResourceNotFoundException if no user is found with the provided ID.
     */
    User updateUser(int userId, User userDetails) throws ResourceNotFoundException;

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to delete.
     * @throws ResourceNotFoundException if no user is found with the provided ID.
     */
    void deleteUser(int userId) throws ResourceNotFoundException;
}
