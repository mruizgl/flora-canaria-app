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
 * Controlador REST para la gestión de usuarios.
 * Este controlador maneja las operaciones CRUD para los usuarios.
 */
@RestController
@RequestMapping("/api/v1")
public class UsersController {

    private UserServiceInterface userService;

    /**
     * Constructor que inyecta el servicio de usuario.
     * 
     * @param userService El servicio que maneja las operaciones de usuario.
     */
    @Autowired
    public void setUserRepository(UserServiceInterface userService) {
        this.userService = userService;
    }

    /**
     * Obtiene todos los usuarios registrados.
     * 
     * @return Una lista de usuarios.
     */
    @Operation(summary = "Get all users")
    @GetMapping("/users/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Obtiene un usuario por su ID.
     * 
     * @param userId El ID del usuario que se desea obtener.
     * @return El usuario correspondiente al ID.
     * @throws ResourceNotFoundException Si no se encuentra el usuario con el ID dado.
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
     * Crea un nuevo usuario.
     * 
     * @param user El objeto usuario que se desea crear.
     * @return El usuario creado.
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
     * Actualiza la información de un usuario.
     * 
     * @param userId El ID del usuario que se desea actualizar.
     * @param userDetails Los detalles actualizados del usuario.
     * @return El usuario actualizado.
     * @throws ResourceNotFoundException Si no se encuentra el usuario con el ID dado.
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
     * @param userId El ID del usuario que se desea eliminar.
     * @return Un mapa con el estado de la eliminación (si fue exitosa o no).
     * @throws ResourceNotFoundException Si no se encuentra el usuario con el ID dado.
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
