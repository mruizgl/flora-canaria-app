package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.iespuerto.mr.flora.model.Role;
import es.iespuerto.mr.flora.service.RoleServiceInterface;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;

/**
 * Implementation of the RoleServiceSoapInterface providing SOAP web service methods
 * for managing roles.
 * 
 * <p>
 * This class is annotated with {@code @WebService} to define it as a SOAP web service endpoint.
 * It uses the {@link RoleServiceInterface} to perform the actual role management operations.
 * </p>
 * 
 * <p>
 * The methods in this class handle exceptions by throwing {@link WebServiceException} with
 * appropriate error messages.
 * </p>
 * 
 * <p>Methods in this class:</p>
 * <ul>
 *   <li>{@link #getAllRoles()} - Retrieves all roles.</li>
 *   <li>{@link #getRoleById(int)} - Retrieves a role by its ID.</li>
 *   <li>{@link #createRole(Role)} - Creates a new role.</li>
 *   <li>{@link #updateRole(int, Role)} - Updates an existing role.</li>
 *   <li>{@link #deleteRole(int)} - Deletes a role by its ID.</li>
 * </ul>
 * 
 * <p>Dependencies:</p>
 * <ul>
 *   <li>{@link RoleServiceInterface} - Service interface for role management operations.</li>
 * </ul>
 * 
 * <p>Exceptions:</p>
 * <ul>
 *   <li>{@link WebServiceException} - Thrown when an error occurs in any of the methods of this service.</li>
 * </ul>
 * 
 * @see RoleServiceSoapInterface
 * @see RoleServiceInterface
 * @see WebServiceException
 */
@WebService(endpointInterface = "es.iespuerto.mr.flora.soap.service.RoleServiceSoapInterface")
public class RoleServiceSoapImpl implements RoleServiceSoapInterface {

    private RoleServiceInterface roleService;

    /**
     * Sets the RoleServiceInterface dependency.
     * 
     * @param roleService The {@link RoleServiceInterface} to be injected.
     */
    @Autowired
    public void setRoleService(RoleServiceInterface roleService) {
        this.roleService = roleService;
    }

    /**
     * Retrieves all roles using the underlying RoleService.
     * 
     * @return A list of all {@link Role} entities.
     * @throws WebServiceException if an error occurs while fetching the roles.
     */
    @Override
    public List<Role> getAllRoles() {
        try {
            return roleService.getAllRols();
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo los roles", e);
        }
    }

    /**
     * Retrieves a role by its ID using the underlying RoleService.
     * 
     * @param roleId the ID of the role to retrieve
     * @return the {@link Role} with the specified ID
     * @throws WebServiceException if an error occurs while fetching the role or the role is not found.
     */
    @Override
    public Role getRoleById(int roleId) {
        try {
            return roleService.getRolById(roleId);
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo el rol con ID: " + roleId, e);
        }
    }

    /**
     * Creates a new role using the underlying RoleService.
     * 
     * @param role The {@link Role} entity to be created.
     * @return The newly created {@link Role} entity.
     * @throws WebServiceException if an error occurs while creating the role.
     */
    @Override
    public Role createRole(Role role) {
        try {
            return roleService.createRol(role);
        } catch (Exception e) {
            throw new WebServiceException("Error creando el rol", e);
        }
    }

    /**
     * Updates an existing role using the underlying RoleService.
     * 
     * @param roleId The ID of the role to be updated.
     * @param roleDetails The new details of the role.
     * @return The updated {@link Role} entity.
     * @throws WebServiceException If the role with the specified ID is not found or an error occurs while updating.
     */
    @Override
    public Role updateRole(int roleId, Role roleDetails) {
        try {
            return roleService.updateRol(roleId, roleDetails);
        } catch (Exception e) {
            throw new WebServiceException("Error actualizando el rol con ID: " + roleId, e);
        }
    }

    /**
     * Deletes a role using the underlying RoleService.
     * 
     * @param roleId The ID of the role to be deleted.
     * @throws WebServiceException If the role with the specified ID is not found or an error occurs while deleting.
     */
    @Override
    public void deleteRole(int roleId) {
        try {
            roleService.deleteRol(roleId);
        } catch (Exception e) {
            throw new WebServiceException("Error eliminando el rol con ID: " + roleId, e);
        }
    }
}
