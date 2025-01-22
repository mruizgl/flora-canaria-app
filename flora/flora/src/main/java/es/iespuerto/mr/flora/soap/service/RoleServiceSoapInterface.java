package es.iespuerto.mr.flora.soap.service;

import es.iespuerto.mr.flora.model.Role;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "springboot.soap.service")
public interface RoleServiceSoapInterface {

    @WebMethod
    @WebResult(name = "role")
    List<Role> getAllRoles();

    @WebMethod
    @WebResult(name = "role")
    Role getRoleById(@WebParam(name = "roleId") int roleId);

    @WebMethod
    @WebResult(name = "role")
    Role createRole(@WebParam(name = "role") Role role);

    @WebMethod
    @WebResult(name = "role")
    Role updateRole(@WebParam(name = "roleId") int roleId,
                    @WebParam(name = "roleDetails") Role roleDetails);

    @WebMethod
    void deleteRole(@WebParam(name = "roleId") int roleId);
}
