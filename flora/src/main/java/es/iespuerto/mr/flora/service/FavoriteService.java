package es.iespuerto.mr.flora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Favorite;
import es.iespuerto.mr.flora.repository.FavoriteRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class for managing favorite plants for users.
 * This class provides methods for adding, retrieving, and deleting favorite plants.
 * 
 * <p>The class uses {@code FavoriteRepository} to interact with the database and is marked as a Spring {@code @Component}
 * to be managed by the Spring container. The service is also annotated with {@code @Slf4j} to enable logging functionality.</p>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@code getAllFavoritePlants()} - Retrieves all favorite plants stored in the database.</li>
 *   <li>{@code getFavoritePlantById(int id)} - Retrieves a specific favorite plant by its ID.</li>
 *   <li>{@code addFavoritePlant(Favorite favoritePlant)} - Adds a new favorite plant to the database.</li>
 *   <li>{@code deleteFavoritePlant(int id)} - Deletes a favorite plant by its ID.</li>
 * </ul>
 * 
 * <p>Annotations:</p>
 * <ul>
 *   <li>{@code @Slf4j}: Provides logging functionality for the class.</li>
 *   <li>{@code @Component}: Marks the class as a Spring bean to be managed by the Spring container.</li>
 *   <li>{@code @Autowired}: Indicates the method to autowire the {@code FavoriteRepository} instance.</li>
 * </ul>
 */
@Slf4j
@Component
public class FavoriteService implements FavoriteServiceInterface {

    private FavoriteRepository favoriteRepository;

    /**
     * Autowired method to set the FavoriteRepository instance.
     * This method is called by Spring to inject the {@code FavoriteRepository} dependency.
     * 
     * @param favoritePlantRepository the FavoriteRepository instance to be set
     */
    @Autowired
    public void setFavoritePlantRepository(FavoriteRepository favoritePlantRepository) {
        this.favoriteRepository = favoritePlantRepository;
    }

    /**
     * Retrieves all favorite plants from the database.
     * Logs an informational message before fetching the data.
     * 
     * @return a list of all favorite plants
     */
    @Override
    public List<Favorite> getAllFavoritePlants() {
        log.info("Obteniendo todos los favoritos de plantas");
        return favoriteRepository.findAll();
    }

    /**
     * Retrieves a specific favorite plant by its ID.
     * If no favorite plant is found for the given ID, a {@code ResourceNotFoundException} is thrown.
     * 
     * @param id the ID of the favorite plant to retrieve
     * @return the favorite plant with the specified ID
     * @throws ResourceNotFoundException if the favorite plant with the given ID does not exist
     */
    @Override
    public Favorite getFavoritePlantById(int id) throws ResourceNotFoundException {
        return favoriteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Favorito de planta no encontrado para este id :: " + id));
    }

    /**
     * Adds a new favorite plant to the database.
     * Logs an informational message before saving the favorite plant.
     * 
     * @param favoritePlant the favorite plant to add
     * @return the added favorite plant
     */
    @Override
    public Favorite addFavoritePlant(Favorite favoritePlant) {
        log.info("Agregando un nuevo favorito de planta");
        return favoriteRepository.save(favoritePlant);
    }

    /**
     * Deletes a favorite plant by its ID.
     * If no favorite plant is found for the given ID, a {@code ResourceNotFoundException} is thrown.
     * 
     * @param id the ID of the favorite plant to delete
     * @throws ResourceNotFoundException if the favorite plant with the given ID does not exist
     */
    @Override
    public void deleteFavoritePlant(int id) throws ResourceNotFoundException {
        Favorite favoritePlant = favoriteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Favorito de planta no encontrado para este id :: " + id));

        favoriteRepository.delete(favoritePlant);
    }
}
