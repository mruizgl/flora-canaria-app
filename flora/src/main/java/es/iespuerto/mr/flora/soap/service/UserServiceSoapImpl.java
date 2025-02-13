package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import es.iespuerto.mr.flora.model.User;
import es.iespuerto.mr.flora.service.UserServiceInterface;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;

/**
 * Implementation of the UserServiceSoapInterface for handling SOAP web service requests.
 * <p>This class provides methods to perform CRUD operations on User entities.</p>
 * <p>It uses the {@link UserServiceInterface} to delegate the actual business logic.</p>
 * 
 * <p>Exceptions are caught and rethrown as {@link WebServiceException} with appropriate messages.</p>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #getAllUsers()} - Retrieves all users from the service.</li>
 *   <li>{@link #getUserById(int)} - Retrieves a user by their ID from the service.</li>
 *   <li>{@link #createUser(User)} - Creates a new user in the service.</li>
 *   <li>{@link #updateUser(int, User)} - Updates an existing user by their ID in the service.</li>
 *   <li>{@link #deleteUser(int)} - Deletes a user by their ID in the service.</li>
 * </ul>
 * 
 * <p>Dependencies:</p>
 * <ul>
 *   <li>{@link UserServiceInterface} - The service interface for user operations.</li>
 * </ul>
 * 
 * <p>Annotations:</p>
 * <ul>
 *   <li>{@link WebService} - Specifies the endpoint interface for the SOAP web service, making this class the SOAP implementation.</li>
 *   <li>{@link Autowired} - Injects the {@link UserServiceInterface} dependency into the class.</li>
 * </ul>
 * 
 * <p>Exceptions:</p>
 * <ul>
 *   <li>{@link WebServiceException} - Thrown when an error occurs during the execution of a SOAP request.</li>
 * </ul>
 * 
 * @see UserServiceSoapInterface
 * @see UserServiceInterface
 * @see WebServiceException
 */
@WebService(endpointInterface = "es.iespuerto.mr.flora.soap.service.UserServiceSoapInterface")
public class UserServiceSoapImpl implements UserServiceSoapInterface {

    private UserServiceInterface userService;

    /**
     * Sets the {@link UserServiceInterface} dependency for this SOAP service.
     *
     * @param userService the user service to be injected.
     */
    @Autowired
    public void setUserService(UserServiceInterface userService) {
        this.userService = userService;
    }

    /**
     * Retrieves a list of all users.
     *
     * @return a list of all users.
     * @throws WebServiceException if an error occurs while fetching the users.
     */
    @Override
    public List<User> getAllUsers() {
        try {
            return userService.getAllUsers();
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo los usuarios", e);
        }
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve.
     * @return the user with the given ID.
     * @throws WebServiceException if an error occurs while fetching the user or if the user is not found.
     */
    @Override
    public User getUserById(int userId) {
        try {
            return userService.getUserById(userId);
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo el usuario con ID: " + userId, e);
        }
    }

    /**
     * Creates a new user.
     *
     * @param user the user to create.
     * @return the created user.
     * @throws WebServiceException if an error occurs while creating the user.
     */
    @Override
    public User createUser(User user) {
        try {
            return userService.createUser(user);
        } catch (Exception e) {
            throw new WebServiceException("Error creando el usuario", e);
        }
    }

    /**
     * Updates an existing user by their ID.
     *
     * @param userId the ID of the user to update.
     * @param userDetails the new details of the user.
     * @return the updated user.
     * @throws WebServiceException if an error occurs while updating the user.
     */
    @Override
    public User updateUser(int userId, User userDetails) {
        try {
            return userService.updateUser(userId, userDetails);
        } catch (Exception e) {
            throw new WebServiceException("Error actualizando el usuario con ID: " + userId, e);
        }
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to delete.
     * @throws WebServiceException if an error occurs while deleting the user.
     */
    @Override
    public void deleteUser(int userId) {
        try {
            userService.deleteUser(userId);
        } catch (Exception e) {
            throw new WebServiceException("Error eliminando el usuario con ID: " + userId, e);
        }
    }
}
