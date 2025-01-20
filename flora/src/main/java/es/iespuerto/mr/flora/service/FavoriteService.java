package es.iespuerto.mr.flora.service;

import java.util.List;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Favorite;
import es.iespuerto.mr.flora.repository.FavoriteRepository;
import org.apache.cxf.annotations.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FavoriteService implements FavoriteServiceInterface {

    private FavoriteRepository favoriteRepository;

    @Autowired
    public void setFavoritePlantRepository(FavoriteRepository favoritePlantRepository) {
        this.favoriteRepository = favoriteRepository;
    }


    @Override
    public List<Favorite> getAllFavoritePlants() {
        log.info("Obteniendo todos los favoritos de plantas");
        return favoriteRepository.findAll();
    }

    @Override
    public Favorite getFavoritePlantById(int id) throws ResourceNotFoundException {
        return favoriteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Favorito de planta no encontrado para este id :: " + id));
    }

    @Override
    public Favorite addFavoritePlant(Favorite favoritePlant) {
        log.info("Agregando un nuevo favorito de planta");
        return favoriteRepository.save(favoritePlant);
    }

    @Override
    public void deleteFavoritePlant(int id) throws ResourceNotFoundException {
        Favorite favoritePlant = favoriteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Favorito de planta no encontrado para este id :: " + id));

        favoriteRepository.delete(favoritePlant);
    }
}
