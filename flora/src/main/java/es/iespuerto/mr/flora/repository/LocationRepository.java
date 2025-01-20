package es.iespuerto.mr.flora.repository;

import es.iespuerto.mr.flora.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}
