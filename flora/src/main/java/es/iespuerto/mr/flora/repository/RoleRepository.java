package es.iespuerto.mr.flora.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iespuerto.mr.flora.model.Role;

/**
 * Interfaz del repositorio para gestionar entidades {@link Role}.
 * <p>Esta interfaz extiende {@link JpaRepository} para proporcionar operaciones CRUD y métodos de consulta adicionales 
 * para la entidad {@link Role}.</p>
 * 
 * <p>El repositorio permite realizar operaciones de acceso a datos como guardar, eliminar, actualizar y buscar roles,
 * además de permitir consultas personalizadas.</p>
 * 
 * @see JpaRepository
 * @see Role
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    /**
     * Encuentra un rol por su nombre.
     * 
     * @param name El nombre del rol que se desea buscar.
     * @return Un {@link Optional} que contiene el rol si se encuentra, o vacío si no se encuentra.
     */
    Optional<Role> findByName(String name);
}
