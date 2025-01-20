package es.iespuerto.mr.flora.repository;

import es.iespuerto.mr.flora.model.Favorite;
import es.iespuerto.mr.flora.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
}
