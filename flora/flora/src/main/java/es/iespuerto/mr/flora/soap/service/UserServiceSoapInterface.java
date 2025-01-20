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
    @WebResult(
            name="user")
    List<User> getAllUsers();

    @WebMethod
    User getUserById(@WebParam(name = "userId") int userId);

}