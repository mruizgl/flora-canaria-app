package es.iespuerto.mr.flora_app.soap.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import es.iespuerto.mr.flora_app.model.User;

import java.util.List;

@WebService(targetNamespace = "springboot.soap.service")
public interface RolesServiceSoapInterface {

    @WebMethod
    @WebResult(
            name = "roles")
    List<User> getRols();

}
