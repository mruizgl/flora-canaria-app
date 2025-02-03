package es.iespuerto.mr.flora.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Representa un rol en el sistema. Un rol es una entidad que puede ser asignada a múltiples {@link User}.
 * Esta clase está mapeada a la tabla 'roles' en la base de datos y contiene atributos como un identificador único (id),
 * el nombre del rol, y un conjunto de usuarios que tienen asignado este rol.
 * 
 * @see User
 */
@Entity
@Table(name = "roles")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role {

    private int id;
    private String name;
    private Set<User> users;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Role() {}

    /**
     * Constructor para crear un rol con un nombre específico.
     * 
     * @param name El nombre del rol.
     */
    public Role(String name) {
        this.name = name;
    }

    /**
     * Obtiene el identificador único del rol.
     * 
     * @return El identificador del rol.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único del rol.
     * 
     * @param id El identificador del rol.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del rol.
     * 
     * @return El nombre del rol.
     */
    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del rol.
     * 
     * @param name El nombre del rol.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene los usuarios asociados a este rol.
     * 
     * @return Un conjunto de {@link User} que representan los usuarios asignados a este rol.
     */
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonBackReference
    public Set<User> getUsers() {
        return users;
    }

    /**
     * Establece los usuarios asignados a este rol.
     * 
     * @param users Un conjunto de {@link User} que representan los usuarios que tendrán este rol.
     */
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /**
     * Devuelve una representación en cadena del rol.
     * 
     * @return Una cadena que representa el rol, incluyendo su id y nombre.
     */
    @Override
    public String toString() {
        return "Role [id=" + id + ", name=" + name + "]";
    }
}
