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
import jakarta.validation.Valid;

/**
 * Controlador encargado de gestionar los roles dentro de la aplicación.
 * Permite obtener, crear, actualizar y eliminar roles a través de una API RESTful.
 *
 * @author Melissa Ruiz
 */
@RestController
@RequestMapping("/api/v1")
public class RolsController {

    private RoleServiceInterface roleService;

    /**
     * Inyección de dependencias para el servicio de roles.
     *
     * @param roleService el servicio que gestiona las operaciones de roles.
     */
    @Autowired
    public void setRoleService(RoleServiceInterface roleService) {
        this.roleService = roleService;
    }

    /**
     * Obtiene todos los roles registrados en el sistema.
     *
     * @return una lista de objetos {@link Role} representando todos los roles disponibles.
     */
    @Operation(summary = "Get all roles")
    @GetMapping("/roles/")
    public List<Role> getAllRoles() {
        return roleService.getAllRols();
    }

    /**
     * Obtiene un rol por su ID.
     *
     * @param roleId el ID del rol a obtener.
     * @return una respuesta {@link ResponseEntity} con el rol solicitado.
     * @throws ResourceNotFoundException si no se encuentra el rol con el ID proporcionado.
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
     * Crea un nuevo rol en el sistema.
     *
     * @param role el objeto {@link Role} que contiene los detalles del nuevo rol.
     * @return el rol creado.
     */
    @Operation(summary = "Insert role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add/role/")
    public Role createRole(@Valid @RequestBody Role role) {
        return roleService.createRol(role);
    }

    /**
     * Actualiza los detalles de un rol existente.
     *
     * @param roleId el ID del rol a actualizar.
     * @param roleDetails el objeto {@link Role} con los nuevos detalles del rol.
     * @return una respuesta {@link ResponseEntity} con el rol actualizado.
     * @throws ResourceNotFoundException si no se encuentra el rol con el ID proporcionado.
     */
    @Operation(summary = "Update role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role updated successfully"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @PutMapping("/update/role/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable(value = "id") int roleId,
                                           @Valid @RequestBody Role roleDetails) throws ResourceNotFoundException {
        final Role updatedRole = roleService.updateRol(roleId, roleDetails);
        return ResponseEntity.ok(updatedRole);
    }

    /**
     * Elimina un rol por su ID.
     *
     * @param roleId el ID del rol a eliminar.
     * @return un mapa con la clave "deleted" y el valor {@code true} si el rol fue eliminado exitosamente.
     * @throws ResourceNotFoundException si no se encuentra el rol con el ID proporcionado.
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
