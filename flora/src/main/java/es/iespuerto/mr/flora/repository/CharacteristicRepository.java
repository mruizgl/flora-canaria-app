package es.iespuerto.mr.flora.repository;

import es.iespuerto.mr.flora.model.Characteristic;
import es.iespuerto.mr.flora.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristic, Integer> {
}
