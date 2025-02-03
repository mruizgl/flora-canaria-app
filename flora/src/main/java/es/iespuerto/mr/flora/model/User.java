package es.iespuerto.mr.flora.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents a user in the system.
 * <p>This class defines the properties and relationships for a user entity that will be persisted in the "users" table.</p>
 * 
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Entity} - Marks this class as a JPA entity, meaning it corresponds to a table in the database.</li>
 *   <li>{@code @Table} - Specifies the name of the table in the database for this entity.</li>
 *   <li>{@code @JsonInclude(JsonInclude.Include.NON_NULL)} - Indicates that null properties should be excluded from the JSON serialization.</li>
 *   <li>{@code @Id} - Marks the id field as the primary key for this entity.</li>
 *   <li>{@code @GeneratedValue} - Specifies that the id field should be automatically generated.</li>
 *   <li>{@code @ManyToOne} - Defines a many-to-one relationship between users and roles.</li>
 *   <li>{@code @JoinColumn} - Specifies the foreign key column in the database for the relationship.</li>
 *   <li>{@code @JsonManagedReference} - Used to manage the serialization of the relationship to prevent circular references.</li>
 * </ul>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #getId()} - Gets the user's unique identifier.</li>
 *   <li>{@link #setId(int)} - Sets the user's unique identifier.</li>
 *   <li>{@link #getName()} - Gets the user's name.</li>
 *   <li>{@link #setName(String)} - Sets the user's name.</li>
 *   <li>{@link #getPassword()} - Gets the user's password.</li>
 *   <li>{@link #setPassword(String)} - Sets the user's password.</li>
 *   <li>{@link #getRole()} - Gets the user's role.</li>
 *   <li>{@link #setRole(Role)} - Sets the user's role.</li>
 *   <li>{@link #toString()} - Returns a string representation of the user object.</li>
 * </ul>
 * 
 * @see Role
 */
@Entity
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private int id;
    private String name;
    private String password; 
    private Role role;  
    
    /**
     * Default constructor.
     * Initializes a new instance of the {@link User} class.
     */
    public User() {}

    /**
     * Constructor with name and password.
     * 
     * @param name The name of the user.
     * @param password The password of the user.
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * Gets the user's unique identifier.
     * 
     * @return The user's unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    /**
     * Sets the user's unique identifier.
     * 
     * @param id The new unique identifier for the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the user's name.
     * 
     * @return The user's name.
     */
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     * 
     * @param name The new name for the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user's password.
     * 
     * @return The user's password.
     */
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     * 
     * @param password The new password for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's role.
     * 
     * @return The user's role.
     */
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false) 
    @JsonManagedReference  
    public Role getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     * 
     * @param role The new role for the user.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Returns a string representation of the user.
     * 
     * @return A string representation of the user object.
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
    }
}
