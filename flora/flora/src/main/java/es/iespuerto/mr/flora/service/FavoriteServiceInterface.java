package es.iespuerto.mr.flora.service;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Favorite;

import java.util.List;

public interface FavoriteServiceInterface {
    List<Favorite> getAllFavoritePlants();
    Favorite getFavoritePlantById(int id) throws ResourceNotFoundException;;
    Favorite addFavoritePlant(Favorite favoritePlant);
    void deleteFavoritePlant(int id) throws ResourceNotFoundException;
}
