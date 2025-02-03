package es.iespuerto.mr.flora.service;

import java.util.List;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Favorite;

/**
 * Interface for managing favorite plants.
 */
public interface FavoriteServiceInterface {

    /**
     * Retrieves all favorite plants.
     *
     * @return a list of all favorite plants.
     */
    List<Favorite> getAllFavoritePlants();

    /**
     * Retrieves a favorite plant by its ID.
     *
     * @param id the ID of the favorite plant to retrieve.
     * @return the favorite plant with the specified ID.
     * @throws ResourceNotFoundException if the favorite plant with the specified ID is not found.
     */
    Favorite getFavoritePlantById(int id) throws ResourceNotFoundException;

    /**
     * Adds a new favorite plant.
     *
     * @param favoritePlant the favorite plant to add.
     * @return the added favorite plant.
     */
    Favorite addFavoritePlant(Favorite favoritePlant);

    /**
     * Deletes a favorite plant by its ID.
     *
     * @param id the ID of the favorite plant to delete.
     * @throws ResourceNotFoundException if the favorite plant with the specified ID is not found.
     */
    void deleteFavoritePlant(int id) throws ResourceNotFoundException;
}
