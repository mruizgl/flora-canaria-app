package es.iespuerto.mr.flora.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Role;
import es.iespuerto.mr.flora.service.RoleServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Controlador REST para gestionar las operaciones relacionadas con los {@link Role}.
 * Este controlador ofrece operaciones CRUD para los roles, tales como obtener todos los roles, obtener un rol por ID o nombre, 
 * crear, actualizar y eliminar roles.
 * 
 * @see Role
 * @see RoleServiceInterface
 */
@RestController
@RequestMapping("/api/v1")
public class RoleController {

    private RoleServiceInterface roleService;

    /**
     * Inyecta el servicio de roles necesario para las operaciones CRUD.
     * 
     * @param roleService El servicio que gestiona la lógica de negocio para los roles.
     */
    @Autowired
    public void setRoleService(RoleServiceInterface roleService) {
        this.roleService = roleService;
    }

    /**
     * Obtiene todos los roles registrados en el sistema.
     * 
     * @return Una lista de {@link Role} que contiene todos los roles disponibles.
     */
    @Operation(summary = "Get all roles")
    @GetMapping("/roles/")
    public List<Role> getAllRoles() {
        return roleService.getAllRols();
    }

    /**
     * Obtiene un rol por su ID.
     * 
     * @param roleId El ID del rol que se desea obtener.
     * @return Un objeto {@link ResponseEntity} que contiene el rol encontrado.
     * @throws ResourceNotFoundException Si no se encuentra el rol con el ID proporcionado.
     */
    @Operation(summary = "Get role by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @GetMapping("/role/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable(value = "id") int roleId) throws ResourceNotFoundException {
        Role role = roleService.getRolById(roleId);
        return ResponseEntity.ok().body(role);
    }

    /**
     * Obtiene un rol por su nombre.
     * 
     * @param roleName El nombre del rol que se desea obtener.
     * @return Un objeto {@link ResponseEntity} que contiene el rol encontrado.
     * @throws ResourceNotFoundException Si no se encuentra el rol con el nombre proporcionado.
     */
    @Operation(summary = "Get role by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @GetMapping("/role/name/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable(value = "name") String roleName) throws ResourceNotFoundException {
        Role role = roleService.getRoleByName(roleName);
        return ResponseEntity.ok().body(role);
    }

    /**
     * Crea un nuevo rol en el sistema.
     * 
     * @param role El objeto {@link Role} que contiene la información del rol a crear.
     * @return El rol creado.
     */
    @Operation(summary = "Insert role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add/role/")
    public Role createRole(@RequestBody Role role) {
        return roleService.createRol(role);
    }

    /**
     * Actualiza la información de un rol existente.
     * 
     * @param roleId El ID del rol que se desea actualizar.
     * @param roleDetails El objeto {@link Role} que contiene los nuevos datos del rol.
     * @return El rol actualizado.
     * @throws ResourceNotFoundException Si no se encuentra el rol con el ID proporcionado.
     */
    @Operation(summary = "Update role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role updated successfully"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @PutMapping("/update/role/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable(value = "id") int roleId,
                                           @RequestBody Role roleDetails) throws ResourceNotFoundException {
        final Role updatedRole = roleService.updateRol(roleId, roleDetails);
        return ResponseEntity.ok(updatedRole);
    }

    /**
     * Elimina un rol del sistema.
     * 
     * @param roleId El ID del rol que se desea eliminar.
     * @return Un mapa con el estado de la operación (eliminado o no).
     * @throws ResourceNotFoundException Si no se encuentra el rol con el ID proporcionado.
     */
    @Operation(summary = "Delete role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @DeleteMapping("/delete/role/{id}")
    public Map<String, Boolean> deleteRole(@PathVariable(value = "id") int roleId) throws ResourceNotFoundException {
        roleService.deleteRol(roleId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
