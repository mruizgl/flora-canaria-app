package es.iespuerto.mr.flora.soap.service;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Favorite;
import es.iespuerto.mr.flora.service.FavoriteServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;

import java.util.List;

@WebService(endpointInterface = "es.iespuerto.mr.flora.soap.service.FavoriteServiceSoapInterface")
public class FavoriteServiceSoapImpl implements FavoriteServiceSoapInterface {

    private FavoriteServiceInterface favoritePlantService;

    @Autowired
    public void setFavoritePlantService(FavoriteServiceInterface favoritePlantService) {
        this.favoritePlantService = favoritePlantService;
    }

    @Override
    public List<Favorite> getAllFavoritePlants() {
        return favoritePlantService.getAllFavoritePlants();
    }

    @Override
    public Favorite getFavoritePlantById(int id) {
        try {
            return favoritePlantService.getFavoritePlantById(id);
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo el favorito de planta", e);
        }
    }

    @Override
    public Favorite addFavoritePlant(Favorite favoritePlant) {
        return favoritePlantService.addFavoritePlant(favoritePlant);
    }

    @Override
    public void deleteFavoritePlant(int id) throws ResourceNotFoundException {
        favoritePlantService.deleteFavoritePlant(id);
    }

}