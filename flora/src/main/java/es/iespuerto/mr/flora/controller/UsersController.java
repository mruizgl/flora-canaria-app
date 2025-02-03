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
import es.iespuerto.mr.flora.model.User;
import es.iespuerto.mr.flora.service.UserServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/**
 * Controlador encargado de gestionar los usuarios dentro de la aplicación.
 * Permite obtener, crear, actualizar y eliminar usuarios a través de una API RESTful.
 *
 * @author Melissa Ruiz
 */
@RestController
@RequestMapping("/api/v1")
public class UsersController {

    private UserServiceInterface userService;

    /**
     * Inyección de dependencias para el servicio de usuarios.
     *
     * @param userService el servicio que gestiona las operaciones de usuarios.
     */
    @Autowired
    public void setUserRepository(UserServiceInterface userService) {
        this.userService = userService;
    }

    /**
     * Obtiene todos los usuarios registrados en el sistema.
     *
     * @return una lista de objetos {@link User} representando todos los usuarios disponibles.
     */
    @Operation(summary = "Get all users")
    @GetMapping("/users/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param userId el ID del usuario a obtener.
     * @return una respuesta {@link ResponseEntity} con el usuario solicitado.
     * @throws ResourceNotFoundException si no se encuentra el usuario con el ID proporcionado.
     */
    @Operation(summary = "Get user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok().body(user);
    }

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param user el objeto {@link User} que contiene los detalles del nuevo usuario.
     * @return el usuario creado.
     */
    @Operation(summary = "Insert user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add/user/")
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * Actualiza los detalles de un usuario existente.
     *
     * @param userId el ID del usuario a actualizar.
     * @param userDetails el objeto {@link User} con los nuevos detalles del usuario.
     * @return una respuesta {@link ResponseEntity} con el usuario actualizado.
     * @throws ResourceNotFoundException si no se encuentra el usuario con el ID proporcionado.
     */
    @Operation(summary = "Update user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/update/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") int userId,
                                           @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        final User updatedUser = userService.updateUser(userId, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param userId el ID del usuario a eliminar.
     * @return un mapa con la clave "deleted" y el valor {@code true} si el usuario fue eliminado exitosamente.
     * @throws ResourceNotFoundException si no se encuentra el usuario con el ID proporcionado.
     */
    @Operation(summary = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/delete/user/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
        userService.deleteUser(userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
