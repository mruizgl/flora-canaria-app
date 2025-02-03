package es.iespuerto.mr.flora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Role;
import es.iespuerto.mr.flora.repository.RoleRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class for managing roles.
 * Provides methods to perform CRUD operations on roles.
 * 
 * <p>
 * This class is annotated with {@code @Component} to indicate that it is a Spring component,
 * and {@code @Slf4j} to enable logging.
 * </p>
 * 
 * <p>Methods in this class:</p>
 * <ul>
 *   <li>{@link #getAllRols()} - Retrieves all roles.</li>
 *   <li>{@link #getRolById(int)} - Retrieves a role by its ID.</li>
 *   <li>{@link #createRol(Role)} - Creates a new role.</li>
 *   <li>{@link #updateRol(int, Role)} - Updates an existing role.</li>
 *   <li>{@link #deleteRol(int)} - Deletes a role by its ID.</li>
 * </ul>
 * 
 * <p>Dependencies:</p>
 * <ul>
 *   <li>{@link RoleRepository} - Repository for accessing role data.</li>
 * </ul>
 * 
 * <p>Exceptions:</p>
 * <ul>
 *   <li>{@link ResourceNotFoundException} - Thrown when a role is not found by its ID.</li>
 * </ul>
 * 
 * <p>Annotations:</p>
 * <ul>
 *   <li>{@code @Autowired} - Used to inject the {@link RoleRepository} dependency.</li>
 *   <li>{@code @PathVariable} - Used to extract the role ID from the URL path.</li>
 *   <li>{@code @Valid} - Used to validate the role object.</li>
 *   <li>{@code @RequestBody} - Used to bind the HTTP request body to the role object.</li>
 * </ul>
 * 
 * @see RoleRepository
 * @see Role
 * @see ResourceNotFoundException
 */
@Slf4j
@Component
public class RoleService implements RoleServiceInterface {
    
    private RoleRepository roleRepository;

    /**
     * Sets the RoleRepository dependency.
     * 
     * @param roleRepository The {@link RoleRepository} to be injected.
     */
    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Retrieves all roles from the repository.
     * 
     * @return A list of all {@link Role} entities.
     */
    @Override
    public List<Role> getAllRols() {
        log.info("Realizando la llamada al servicio");
        return roleRepository.findAll();
    }

    /**
     * Retrieves a Role by its ID.
     *
     * @param roleId the ID of the role to retrieve
     * @return the {@link Role} with the specified ID
     * @throws ResourceNotFoundException if no role is found with the specified ID
     */
    @Override
    public Role getRolById(@PathVariable(value = "id") int roleId) throws ResourceNotFoundException {
        return roleRepository.getById(roleId);
    }

    /**
     * Creates a new role in the repository.
     * 
     * @param role The {@link Role} entity to be created.
     * @return The newly created {@link Role} entity.
     */
    @Override
    public Role createRol(@Valid @RequestBody Role role) {
        return roleRepository.save(role);
    }

    /**
     * Updates an existing role in the repository.
     *
     * @param rolId The ID of the role to be updated.
     * @param roleDetails The new details of the role.
     * @return The updated {@link Role} entity.
     * @throws ResourceNotFoundException If the role with the specified ID is not found.
     */
    @Override
    public Role updateRol(@PathVariable(value = "id") int rolId, @Valid @RequestBody Role roleDetails) throws ResourceNotFoundException {
        Role role = roleRepository.findById(rolId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + rolId));

        role.setName(roleDetails.getName());
        return roleRepository.save(role);
    }

    /**
     * Deletes a role from the repository by its ID.
     *
     * @param rolId The ID of the role to be deleted.
     * @throws ResourceNotFoundException If the role with the specified ID is not found.
     */
    @Override
    public void deleteRol(@PathVariable(value = "id") int rolId) throws ResourceNotFoundException {
        Role role = roleRepository.findById(rolId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + rolId));

        roleRepository.delete(role);
    }
}
