package es.iespuerto.mr.flora.soap.service;

import es.iespuerto.mr.flora.model.User;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "springboot.soap.service")
public interface UserServiceSoapInterface {

    @WebMethod
    @WebResult(name = "user")
    List<User> getAllUsers();

    @WebMethod
    @WebResult(name = "user")
    User getUserById(@WebParam(name = "userId") int userId);

    @WebMethod
    @WebResult(name = "user")
    User createUser(@WebParam(name = "user") User user);

    @WebMethod
    @WebResult(name = "user")
    User updateUser(@WebParam(name = "userId") int userId,
                    @WebParam(name = "userDetails") User userDetails);

    @WebMethod
    void deleteUser(@WebParam(name = "userId") int userId);
}
