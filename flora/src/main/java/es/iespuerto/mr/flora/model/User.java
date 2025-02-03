package es.iespuerto.mr.flora.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing a User.
 * 
 * <p>This class is mapped to the "users" table in the database and represents a user with an ID and a name.</p>
 * 
 * <p>Annotations:</p>
 * <ul>
 *   <li>{@code @Entity}: Specifies that the class is an entity and is mapped to a database table.</li>
 *   <li>{@code @Table}: Specifies the name of the database table to be used for mapping (in this case, "users").</li>
 *   <li>{@code @Id}: Specifies the primary key of the entity.</li>
 *   <li>{@code @GeneratedValue}: Defines the strategy for generating the primary key values automatically.</li>
 *   <li>{@code @Column}: Specifies the column in the database to map the field (in this case, the "name" column).</li>
 * </ul>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #getId()}: Returns the user's ID.</li>
 *   <li>{@link #setId(int)}: Sets the user's ID.</li>
 *   <li>{@link #getName()}: Returns the user's name.</li>
 *   <li>{@link #setName(String)}: Sets the user's name.</li>
 *   <li>{@link #toString()}: Returns a string representation of the User object in the format "User [id=..., name=...]".</li>
 * </ul>
 * 
 * <p>Constructors:</p>
 * <ul>
 *   <li>{@link User()}: Default constructor.</li>
 *   <li>{@link User(String)}: Constructor with a name parameter to initialize a user with a specific name.</li>
 * </ul>
 * 
 * @see jakarta.persistence.Entity
 * @see jakarta.persistence.Table
 * @see jakarta.persistence.Id
 * @see jakarta.persistence.GeneratedValue
 * @see jakarta.persistence.Column
 */
@Entity
@Table(name = "users")
public class User {

    private int id;
    private String name;

    /**
     * Default constructor for creating a new User.
     */
    public User() {
    }

    /**
     * Constructor with name parameter to initialize a User with a specific name.
     *
     * @param name the name of the user.
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Gets the ID of the user.
     * 
     * @return the user's ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     * 
     * @param id the ID to set for the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the user.
     * 
     * @return the user's name.
     */
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     * 
     * @param name the name to set for the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the User object.
     * 
     * @return a string representation of the User object in the format "User [id=..., name=...]".
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + "]";
    }
}
