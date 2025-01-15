package es.iespuerto.mr.flora_app.soap.service;

import es.iespuerto.mr.flora_app.model.User;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@WebService(endpointInterface = "es.system.jpexposito.springboot.soap.service.RolesServiceSoapInterface")
public class RolesServiceSoapImpl implements RolesServiceSoapInterface {

    @Autowired
    private UserServiceSoapInterface userService;

    @Override
    public List<User> getRols() {
        return userService.getAllUsers();
    }

}
