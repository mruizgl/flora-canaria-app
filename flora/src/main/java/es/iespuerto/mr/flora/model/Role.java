package es.iespuerto.mr.flora.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a role entity with an id and a name.
 * This class is mapped to the "roles" table in the database.
 * 
 * <p>
 * Annotations:
 * <ul>
 *   <li>{@link Entity} - Specifies that the class is an entity and is mapped to a database table.</li>
 *   <li>{@link Table} (name = "roles") - Specifies the name of the database table to be used for mapping.</li>
 *   <li>{@link Id} - Specifies the primary key of an entity.</li>
 *   <li>{@link GeneratedValue} (strategy = GenerationType.AUTO) - Provides for the specification of generation strategies for the values of primary keys.</li>
 *   <li>{@link Column} (name = "name", nullable = false) - Specifies the mapped column for a persistent property or field.</li>
 * </ul>
 * 
 * Fields:
 * <ul>
 *   <li>{@code id} - The unique identifier for the role.</li>
 *   <li>{@code name} - The name of the role.</li>
 * </ul>
 * 
 * Constructors:
 * <ul>
 *   <li>{@link Role()} - Default constructor.</li>
 *   <li>{@link Role(String name)} - Constructor with name parameter.</li>
 * </ul>
 * 
 * Methods:
 * <ul>
 *   <li>{@link #getId()} - Returns the id of the role.</li>
 *   <li>{@link #setId(int id)} - Sets the id of the role.</li>
 *   <li>{@link #getName()} - Returns the name of the role.</li>
 *   <li>{@link #setName(String name)} - Sets the name of the role.</li>
 *   <li>{@link #toString()} - Returns a string representation of the role.</li>
 * </ul>
 */
@Entity
@Table(name = "roles")
public class Role {

    private int id;
    private String name;

    /**
     * Default constructor for Role.
     */
    public Role() {
    }

    /**
     * Constructor with name parameter to create a Role with a specific name.
     * 
     * @param name The name of the role.
     */
    public Role(String name) {
        this.name = name;
    }

    /**
     * Gets the unique identifier for the role.
     * 
     * @return The id of the role.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the role.
     * 
     * @param id The id to be set for the role.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the role.
     * 
     * @return The name of the role.
     */
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the role.
     * 
     * @param name The name to be set for the role.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the role.
     * 
     * @return A string representing the role with id and name.
     */
    @Override
    public String toString() {
        return "Role [id=" + id + ", name=" + name + "]";
    }
}
