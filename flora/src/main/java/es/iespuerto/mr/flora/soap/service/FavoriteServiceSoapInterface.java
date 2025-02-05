package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Favorite;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

/**
 * This interface defines the SOAP web service operations for managing favorite plants.
 * It provides methods to retrieve all favorite plants, get a favorite plant by its ID,
 * add a new favorite plant, and delete a favorite plant by its ID.
 * 
 * <p>The service is annotated with {@code @WebService} to indicate that it is a SOAP web service.</p>
 * <p>Each method is annotated with {@code @WebMethod} to expose it as a web service operation.</p>
 * <p>{@code @WebResult} specifies the name of the result element in the SOAP response, and 
 * {@code @WebParam} is used to specify the names of the parameters in the SOAP request.</p>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@code getAllFavoritePlants()} - Retrieves a list of all favorite plants.</li>
 *   <li>{@code getFavoritePlantById(int id)} - Retrieves a specific favorite plant by its ID.</li>
 *   <li>{@code addFavoritePlant(Favorite favoritePlant)} - Adds a new favorite plant to the database.</li>
 *   <li>{@code deleteFavoritePlant(int id)} - Deletes a favorite plant by its ID.</li>
 * </ul>
 * 
 * <p>Exceptions:</p>
 * <ul>
 *   <li>{@code ResourceNotFoundException} - Thrown when attempting to delete a favorite plant that does not exist.</li>
 * </ul>
 * 
 * <p>Annotations:</p>
 * <ul>
 *   <li>{@code @WebService}: Marks the interface as a SOAP web service.</li>
 *   <li>{@code @WebMethod}: Marks the method as a web service operation.</li>
 *   <li>{@code @WebResult}: Specifies the name of the result element in the SOAP response.</li>
 *   <li>{@code @WebParam}: Specifies the name of the parameter in the SOAP request.</li>
 * </ul>
 */
@WebService(targetNamespace = "springboot.soap.service")
public interface FavoriteServiceSoapInterface {

    /**
     * Retrieves all favorite plants from the system.
     * 
     * @return a list of all favorite plants
     */
    @WebMethod
    @WebResult(name = "favoritePlant")
    List<Favorite> getAllFavoritePlants() throws ResourceNotFoundException;

    /**
     * Retrieves a specific favorite plant by its ID.
     * 
     * @param id the ID of the favorite plant to retrieve
     * @return the favorite plant with the specified ID
     */
    @WebMethod
    @WebResult(name = "favoritePlant")
    Favorite getFavoritePlantById(@WebParam(name = "id") int id);

    /**
     * Adds a new favorite plant to the system.
     * 
     * @param favoritePlant the favorite plant to add
     * @return the added favorite plant
     */
    @WebMethod
    @WebResult(name = "favoritePlant")
    Favorite addFavoritePlant(@WebParam(name = "favoritePlant") Favorite favoritePlant);

    /**
     * Deletes a favorite plant by its ID.
     * 
     * @param id the ID of the favorite plant to delete
     * @throws ResourceNotFoundException if the favorite plant with the given ID does not exist
     */
    @WebMethod
    void deleteFavoritePlant(@WebParam(name = "id") int id) throws ResourceNotFoundException;
}
