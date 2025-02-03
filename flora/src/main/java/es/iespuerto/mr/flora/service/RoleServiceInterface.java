package es.iespuerto.mr.flora.service;

import java.util.List;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Role;

/**
 * Interface for Role Service operations.
 * Provides methods to perform CRUD operations on Role entities.
 * 
 * <p>
 * This interface defines the operations that a service layer should implement
 * to manage {@link Role} entities. These methods include retrieving, creating,
 * updating, and deleting roles in the system.
 * </p>
 * 
 * Methods:
 * <ul>
 *   <li>{@link #getAllRols()} - Retrieves a list of all roles.</li>
 *   <li>{@link #getRolById(int)} - Retrieves a role by its ID.</li>
 *   <li>{@link #createRol(Role)} - Creates a new role.</li>
 *   <li>{@link #updateRol(int, Role)} - Updates an existing role by its ID.</li>
 *   <li>{@link #deleteRol(int)} - Deletes a role by its ID.</li>
 * </ul>
 * 
 * Exceptions:
 * <ul>
 *   <li>{@link ResourceNotFoundException} - Thrown when a role is not found, typically when attempting
 *       to retrieve or update a role that does not exist.</li>
 * </ul>
 * 
 * @see Role
 * @see ResourceNotFoundException
 */
public interface RoleServiceInterface {
    
    /**
     * Retrieves a list of all roles.
     * 
     * @return A list of {@link Role} entities.
     */
    List<Role> getAllRols();

    /**
     * Retrieves a role by its ID.
     * 
     * @param roleId The ID of the role to be retrieved.
     * @return The {@link Role} entity with the specified ID.
     * @throws ResourceNotFoundException If the role with the given ID does not exist.
     */
    Role getRolById(int roleId) throws ResourceNotFoundException;

    /**
     * Creates a new role.
     * 
     * @param role The {@link Role} entity to be created.
     * @return The newly created {@link Role} entity.
     */
    Role createRol(Role role);

    /**
     * Updates an existing role by its ID.
     * 
     * @param rolId The ID of the role to be updated.
     * @param roleDetails The new details of the role.
     * @return The updated {@link Role} entity.
     * @throws ResourceNotFoundException If the role with the given ID does not exist.
     */
    Role updateRol(int rolId, Role roleDetails) throws ResourceNotFoundException;

    /**
     * Deletes a role by its ID.
     * 
     * @param rolId The ID of the role to be deleted.
     * @throws ResourceNotFoundException If the role with the given ID does not exist.
     */
    void deleteRol(int rolId) throws ResourceNotFoundException;

    /**
     * Deletes a role by its name.
     * 
     * @param rolId The name of the role to be deleted.
     * @throws ResourceNotFoundException If the role with the given name does not exist.
     */
    Role getRoleByName(String roleName) throws ResourceNotFoundException;
}
