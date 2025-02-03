package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import es.iespuerto.mr.flora.model.Role;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

/**
 * This interface defines the SOAP web service operations for managing roles.
 * It provides methods to retrieve, create, update, and delete roles.
 * 
 */
@WebService(targetNamespace = "springboot.soap.service")
public interface RoleServiceSoapInterface {

    /**
     * Retrieves all roles.
     * 
     * @return a list of all roles.
     */
    @WebMethod
    @WebResult(name = "role")
    List<Role> getAllRoles();

    /**
     * Retrieves a role by its ID.
     * 
     * @param roleId the ID of the role to retrieve.
     * @return the role with the specified ID.
     */
    @WebMethod
    @WebResult(name = "role")
    Role getRoleById(@WebParam(name = "roleId") int roleId);

    /**
     * Creates a new role.
     * 
     * @param role the role to create.
     * @return the created role.
     */
    @WebMethod
    @WebResult(name = "role")
    Role createRole(@WebParam(name = "role") Role role);

    /**
     * Updates an existing role.
     * 
     * @param roleId the ID of the role to update.
     * @param roleDetails the new details of the role.
     * @return the updated role.
     */
    @WebMethod
    @WebResult(name = "role")
    Role updateRole(@WebParam(name = "roleId") int roleId,
                    @WebParam(name = "roleDetails") Role roleDetails);

    /**
     * Deletes a role by its ID.
     * 
     * @param roleId the ID of the role to delete.
     */
    @WebMethod
    void deleteRole(@WebParam(name = "roleId") int roleId);
}
