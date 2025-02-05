package es.iespuerto.mr.flora.controller;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Favorite;
import es.iespuerto.mr.flora.service.FavoriteServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FavoritesControllerTest {

    @Mock
    private FavoriteServiceInterface favoriteService;

    @InjectMocks
    private FavoritesController favoritesController;

    private Favorite favorite;

    @BeforeEach
    void setUp() {
        favorite = new Favorite();
        favorite.setId(1);
        favorite.setUserId(1);
        favorite.setPlantId(1);
    }

    @Test
    void getAllFavoritesReturnsListOfFavorites() throws ResourceNotFoundException {
        when(favoriteService.getAllFavoritePlants()).thenReturn(List.of(favorite));

        List<Favorite> favorites = favoritesController.getAllFavorites();

        assertNotNull(favorites);
        assertEquals(1, favorites.size());
        assertEquals(1, favorites.get(0).getUserId());
        assertEquals(1, favorites.get(0).getPlantId());
    }

    @Test
    void getAllFavoritesThrowsResourceNotFoundException() throws ResourceNotFoundException {
        when(favoriteService.getAllFavoritePlants()).thenThrow(new ResourceNotFoundException("Favorites not found"));

        assertThrows(ResourceNotFoundException.class, () -> favoritesController.getAllFavorites());
    }

    @Test
    void addFavoriteReturnsCreatedFavorite() {
        when(favoriteService.addFavoritePlant(any(Favorite.class))).thenReturn(favorite);

        Favorite createdFavorite = favoritesController.addFavorite(favorite);

        assertNotNull(createdFavorite);
        assertEquals(1, createdFavorite.getUserId());
        assertEquals(1, createdFavorite.getPlantId());
    }

    @Test
    void deleteFavoriteReturnsSuccessMap() throws ResourceNotFoundException {
        doNothing().when(favoriteService).deleteFavoritePlant(1);

        Map<String, Boolean> response = favoritesController.deleteFavorite(1);

        assertTrue(response.get("deleted"));
    }

    @Test
    void deleteFavoriteThrowsResourceNotFoundException() throws ResourceNotFoundException {
        doThrow(new ResourceNotFoundException("Favorite not found")).when(favoriteService).deleteFavoritePlant(1);

        assertThrows(ResourceNotFoundException.class, () -> favoritesController.deleteFavorite(1));
    }
}