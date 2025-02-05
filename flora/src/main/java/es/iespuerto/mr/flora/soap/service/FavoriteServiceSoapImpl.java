package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Favorite;
import es.iespuerto.mr.flora.service.FavoriteServiceInterface;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;

/**
 * Implementation of the {@link FavoriteServiceSoapInterface}.
 * This class provides SOAP web service methods for managing favorite plants.
 * 
 * <p>This implementation delegates the actual operations to the {@link FavoriteServiceInterface}.</p>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #getAllFavoritePlants()} - Retrieves all favorite plants.</li>
 *   <li>{@link #getFavoritePlantById(int)} - Retrieves a favorite plant by its ID.</li>
 *   <li>{@link #addFavoritePlant(Favorite)} - Adds a new favorite plant.</li>
 *   <li>{@link #deleteFavoritePlant(int)} - Deletes a favorite plant by its ID.</li>
 * </ul>
 * 
 * <p>Exceptions:</p>
 * <ul>
 *   <li>{@link WebServiceException} - Thrown if an error occurs while retrieving a favorite plant by ID.</li>
 *   <li>{@link ResourceNotFoundException} - Thrown if a favorite plant to be deleted is not found.</li>
 * </ul>
 * 
 * <p>Annotations:</p>
 * <ul>
 *   <li>{@link WebService} - Specifies the endpoint interface for the SOAP web service.</li>
 *   <li>{@link Autowired} - Used for dependency injection of the {@link FavoriteServiceInterface}.</li>
 * </ul>
 * 
 * @see FavoriteServiceSoapInterface
 * @see FavoriteServiceInterface
 * @see Favorite
 */
@WebService(endpointInterface = "es.iespuerto.mr.flora.soap.service.FavoriteServiceSoapInterface")
public class FavoriteServiceSoapImpl implements FavoriteServiceSoapInterface {

    private FavoriteServiceInterface favoritePlantService;

    /**
     * Sets the {@link FavoriteServiceInterface} instance for performing the actual operations.
     * This method is annotated with {@link Autowired} for dependency injection.
     * 
     * @param favoritePlantService the service instance to set
     */
    @Autowired
    public void setFavoritePlantService(FavoriteServiceInterface favoritePlantService) {
        this.favoritePlantService = favoritePlantService;
    }

    /**
     * Retrieves all favorite plants.
     * 
     * @return a list of all favorite plants
     */
    @Override
    public List<Favorite> getAllFavoritePlants() throws ResourceNotFoundException {
        return favoritePlantService.getAllFavoritePlants();
    }

    /**
     * Retrieves a favorite plant by its ID.
     * 
     * @param id the ID of the favorite plant to retrieve
     * @return the favorite plant with the specified ID
     * @throws WebServiceException if an error occurs while retrieving the favorite plant
     */
    @Override
    public Favorite getFavoritePlantById(int id) {
        try {
            return favoritePlantService.getFavoritePlantById(id);
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo el favorito de planta", e);
        }
    }

    /**
     * Adds a new favorite plant.
     * 
     * @param favoritePlant the favorite plant to add
     * @return the added favorite plant
     */
    @Override
    public Favorite addFavoritePlant(Favorite favoritePlant) {
        return favoritePlantService.addFavoritePlant(favoritePlant);
    }

    /**
     * Deletes a favorite plant by its ID.
     * 
     * @param id the ID of the favorite plant to delete
     * @throws ResourceNotFoundException if the favorite plant to be deleted is not found
     */
    @Override
    public void deleteFavoritePlant(int id) throws ResourceNotFoundException {
        favoritePlantService.deleteFavoritePlant(id);
    }
}
