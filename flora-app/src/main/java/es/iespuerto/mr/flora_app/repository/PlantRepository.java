package es.iespuerto.mr.flora_app.repository;

import es.iespuerto.mr.flora_app.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant, Integer> {
}
