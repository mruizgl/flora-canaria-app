package es.iespuerto.mr.flora_app.repository;

import es.iespuerto.mr.flora_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}