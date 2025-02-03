package es.iespuerto.mr.flora.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Favorite;
import es.iespuerto.mr.flora.service.FavoriteServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/**
 * Controlador que maneja las operaciones relacionadas con los favoritos.
 * Permite agregar, eliminar y consultar los favoritos.
 * 
 * @author Melissa Ruiz (@mruizgl en github)
 */
@RestController
@RequestMapping("/api/v1")
public class FavoritesController {

    private FavoriteServiceInterface favoriteService;

    /**
     * Constructor que inyecta el servicio de favoritos.
     *
     * @param favoriteService Servicio para manejar los favoritos.
     */
    @Autowired
    public void setFavoriteService(FavoriteServiceInterface favoriteService) {
        this.favoriteService = favoriteService;
    }

    /**
     * Obtiene todos los favoritos.
     *
     * @return Lista de favoritos.
     * @throws ResourceNotFoundException Si no existen favoritos.
     */
    @Operation(summary = "Get all favorites")
    @GetMapping("/favorites/")
    public List<Favorite> getAllFavorites() throws ResourceNotFoundException {
        return favoriteService.getAllFavoritePlants();
    }

    /**
     * Agrega un nuevo favorito.
     *
     * @param favorite Los detalles del favorito a agregar.
     * @return El favorito creado.
     */
    @Operation(summary = "Add favorite")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite added successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add/favorite/")
    public Favorite addFavorite(@Valid @RequestBody Favorite favorite) {
        return favoriteService.addFavoritePlant(favorite);
    }

    /**
     * Elimina un favorito.
     *
     * @param favoriteId El ID del favorito a eliminar.
     * @return Un mapa indicando si el favorito fue eliminado exitosamente.
     * @throws ResourceNotFoundException Si el favorito no existe.
     */
    @Operation(summary = "Delete favorite")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Favorite not found")
    })
    @DeleteMapping("/delete/favorite/{favoriteId}")
    public Map<String, Boolean> deleteFavorite(@PathVariable(value = "favoriteId") int favoriteId) throws ResourceNotFoundException {
        favoriteService.deleteFavoritePlant(favoriteId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
