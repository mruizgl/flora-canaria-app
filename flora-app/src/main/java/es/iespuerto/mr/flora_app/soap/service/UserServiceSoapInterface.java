package es.iespuerto.mr.flora_app.soap.service;

import es.iespuerto.mr.flora_app.model.User;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "springboot.soap.service")
public interface UserServiceSoapInterface {

    @WebMethod
    @WebResult(
            name="user")
    List<User> getAllUsers();

    @WebMethod
    User getUserById(@WebParam(name = "userId") int userId);

    @WebMethod
    boolean deleteUserById(@WebParam(name = "userId") int userId);

    @WebMethod
    boolean deleteUserById2(int userId);
}