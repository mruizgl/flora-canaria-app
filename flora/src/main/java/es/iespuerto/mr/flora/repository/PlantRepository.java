package es.iespuerto.mr.flora.repository;

import es.iespuerto.mr.flora.model.Plant;
import es.iespuerto.mr.flora.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {
}
