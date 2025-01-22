package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import es.iespuerto.mr.flora.model.User;
import es.iespuerto.mr.flora.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;

@WebService(endpointInterface = "es.iespuerto.mr.flora.soap.service.UserServiceSoapInterface")
public class UserServiceSoapImpl implements UserServiceSoapInterface {

    private UserServiceInterface userService;

    @Autowired
    public void setUserService(UserServiceInterface userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userService.getAllUsers();
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo los usuarios", e);
        }
    }

    @Override
    public User getUserById(int userId) {
        try {
            return userService.getUserById(userId);
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo el usuario con ID: " + userId, e);
        }
    }

    @Override
    public User createUser(User user) {
        try {
            return userService.createUser(user);
        } catch (Exception e) {
            throw new WebServiceException("Error creando el usuario", e);
        }
    }

    @Override
    public User updateUser(int userId, User userDetails) {
        try {
            return userService.updateUser(userId, userDetails);
        } catch (Exception e) {
            throw new WebServiceException("Error actualizando el usuario con ID: " + userId, e);
        }
    }

    @Override
    public void deleteUser(int userId) {
        try {
            userService.deleteUser(userId);
        } catch (Exception e) {
            throw new WebServiceException("Error eliminando el usuario con ID: " + userId, e);
        }
    }
}
