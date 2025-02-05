package es.iespuerto.mr.flora.soap.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Favorite;
import es.iespuerto.mr.flora.service.FavoriteServiceInterface;
import jakarta.xml.ws.WebServiceException;

public class FavoriteServiceSoapImplTest {

    @Mock
    private FavoriteServiceInterface favoriteService;

    @InjectMocks
    private FavoriteServiceSoapImpl favoriteServiceSoap;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllFavoritePlantsReturnsAllFavorites() throws ResourceNotFoundException {
        Favorite favorite1 = new Favorite();
        favorite1.setPlantId(1);
        favorite1.setId(1);
        Favorite favorite2 = new Favorite();
        favorite2.setPlantId(2);
        favorite2.setId(2);

        when(favoriteService.getAllFavoritePlants()).thenReturn(Arrays.asList(favorite1, favorite2));

        List<Favorite> favorites = favoriteServiceSoap.getAllFavoritePlants();

        assertEquals(2, favorites.size());
        assertEquals(1, favorites.get(0).getPlantId());
        assertEquals(2, favorites.get(1).getPlantId());
    }



    @Test
    void getFavoritePlantByIdReturnsFavoriteIfExists() throws ResourceNotFoundException {
        Favorite favorite = new Favorite();
        favorite.setId(1);
        favorite.setPlantId(1);

        when(favoriteService.getFavoritePlantById(1)).thenReturn(favorite);

        Favorite foundFavorite = favoriteServiceSoap.getFavoritePlantById(1);

        assertEquals(1, foundFavorite.getId());
        assertEquals(1, foundFavorite.getPlantId());
    }

    @Test
    void getFavoritePlantByIdThrowsWebServiceExceptionIfFavoriteNotFound() throws ResourceNotFoundException {
        when(favoriteService.getFavoritePlantById(1)).thenThrow(new ResourceNotFoundException("Favorite not found"));

        assertThrows(WebServiceException.class, () -> {
            favoriteServiceSoap.getFavoritePlantById(1);
        });
    }

    @Test
    void addFavoritePlantSavesAndReturnsFavorite() {
        Favorite favorite = new Favorite();
        favorite.setPlantId(1);
        favorite.setId(1);

        when(favoriteService.addFavoritePlant(favorite)).thenReturn(favorite);

        Favorite createdFavorite = favoriteServiceSoap.addFavoritePlant(favorite);

        assertEquals(1, createdFavorite.getPlantId());
        assertEquals(1, createdFavorite.getId());
    }



    @Test
    void deleteFavoritePlantDeletesFavoriteIfExists() throws ResourceNotFoundException {
        doNothing().when(favoriteService).deleteFavoritePlant(1);

        favoriteServiceSoap.deleteFavoritePlant(1);

        verify(favoriteService, times(1)).deleteFavoritePlant(1);
    }


}