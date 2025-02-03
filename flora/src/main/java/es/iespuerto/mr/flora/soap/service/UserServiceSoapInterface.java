package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import es.iespuerto.mr.flora.model.User;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

/**
 * This interface defines the SOAP web service operations for managing users.
 * It provides methods to retrieve, create, update, and delete users.
 * 
 */
@WebService(targetNamespace = "springboot.soap.service")
public interface UserServiceSoapInterface {

    /**
     * Retrieves a list of all users.
     * 
     * @return a list of {@link User} objects.
     */
    @WebMethod
    @WebResult(name = "user")
    List<User> getAllUsers();

    /**
     * Retrieves a user by their unique ID.
     * 
     * @param userId the unique ID of the user.
     * @return the {@link User} object corresponding to the given ID.
     */
    @WebMethod
    @WebResult(name = "user")
    User getUserById(@WebParam(name = "userId") int userId);

    /**
     * Creates a new user.
     * 
     * @param user the {@link User} object containing the details of the user to be created.
     * @return the created {@link User} object.
     */
    @WebMethod
    @WebResult(name = "user")
    User createUser(@WebParam(name = "user") User user);

    /**
     * Updates an existing user.
     * 
     * @param userId the unique ID of the user to be updated.
     * @param userDetails the {@link User} object containing the updated details of the user.
     * @return the updated {@link User} object.
     */
    @WebMethod
    @WebResult(name = "user")
    User updateUser(@WebParam(name = "userId") int userId,
                    @WebParam(name = "userDetails") User userDetails);

    /**
     * Deletes a user by their unique ID.
     * 
     * @param userId the unique ID of the user to be deleted.
     */
    @WebMethod
    void deleteUser(@WebParam(name = "userId") int userId);
}
