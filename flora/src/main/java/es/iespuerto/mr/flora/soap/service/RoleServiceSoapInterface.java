package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import es.iespuerto.mr.flora.model.Role;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

@WebService(targetNamespace = "springboot.soap.service")
public interface RoleServiceSoapInterface {

    @WebMethod
    @WebResult(name = "role")
    List<Role> getAllRoles();

    @WebMethod
    Role getRoleById(@WebParam(name = "roleId") int roleId);
}
