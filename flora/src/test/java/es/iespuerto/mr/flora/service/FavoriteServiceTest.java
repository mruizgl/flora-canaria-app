package es.iespuerto.mr.flora.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Favorite;
import es.iespuerto.mr.flora.repository.FavoriteRepository;

public class FavoriteServiceTest {

    @Mock
    private FavoriteRepository favoriteRepository;

    @InjectMocks
    private FavoriteService favoriteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllFavoritePlantsReturnsAllFavorites() {
        Favorite favorite1 = new Favorite();
        favorite1.setId(1);
        Favorite favorite2 = new Favorite();
        favorite2.setId(2);

        when(favoriteRepository.findAll()).thenReturn(Arrays.asList(favorite1, favorite2));

        List<Favorite> favorites = favoriteService.getAllFavoritePlants();

        assertEquals(2, favorites.size());
        assertEquals(1, favorites.get(0).getId());
        assertEquals(2, favorites.get(1).getId());
    }

    @Test
    void getFavoritePlantByIdReturnsFavoriteIfExists() throws ResourceNotFoundException {
        Favorite favorite = new Favorite();
        favorite.setId(1);

        when(favoriteRepository.findById(1)).thenReturn(Optional.of(favorite));

        Favorite foundFavorite = favoriteService.getFavoritePlantById(1);

        assertEquals(1, foundFavorite.getId());
    }

    @Test
    void getFavoritePlantByIdThrowsExceptionIfFavoriteDoesNotExist() {
        when(favoriteRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            favoriteService.getFavoritePlantById(1);
        });
    }

    @Test
    void addFavoritePlantSavesAndReturnsFavorite() {
        Favorite favorite = new Favorite();
        favorite.setId(1);

        when(favoriteRepository.save(favorite)).thenReturn(favorite);

        Favorite createdFavorite = favoriteService.addFavoritePlant(favorite);

        assertEquals(1, createdFavorite.getId());
    }

    @Test
    void deleteFavoritePlantDeletesFavoriteIfExists() throws ResourceNotFoundException {
        Favorite favorite = new Favorite();
        favorite.setId(1);

        when(favoriteRepository.findById(1)).thenReturn(Optional.of(favorite));
        doNothing().when(favoriteRepository).delete(favorite);

        favoriteService.deleteFavoritePlant(1);

        verify(favoriteRepository, times(1)).delete(favorite);
    }

    @Test
    void deleteFavoritePlantThrowsExceptionIfFavoriteDoesNotExist() {
        when(favoriteRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            favoriteService.deleteFavoritePlant(1);
        });
    }
}