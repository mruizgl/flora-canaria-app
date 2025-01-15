package es.iespuerto.mr.flora_app.soap.service;

import es.iespuerto.mr.flora_app.model.User;
import es.iespuerto.mr.flora_app.service.UserServiceInterface;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@WebService(endpointInterface = "es.system.jpexposito.springboot.soap.service.UserServiceSoapInterface")
public class UserServiceSoapImpl implements UserServiceSoapInterface{

    private UserServiceInterface userService;

    @Autowired
    public void setUserRepository(UserServiceInterface userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public User getUserById(int userId) {
        try {
            return userService.getUserById(userId);
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo el usuario", e);
        }
    }

    @Override
    public boolean deleteUserById(int userId) {
        try {
            userService.deleteUser(userId);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean deleteUserById2(int userId) {
        try {
            userService.deleteUser(userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}