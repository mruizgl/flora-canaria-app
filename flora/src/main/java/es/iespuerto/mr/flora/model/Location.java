package es.iespuerto.mr.flora.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a location entity with an id and a name.
 * This class is mapped to the "locations" table in the database.
 * 
 * <p>
 * The {@code Location} class provides constructors to create a location
 * with a specified name or without any parameters. It includes getter and
 * setter methods for the id and name properties, which are annotated for
 * JPA (Java Persistence API) to handle database operations.
 * </p>
 * 
 * <p>
 * The {@code getId} method is annotated with {@code @Id} and {@code @GeneratedValue}
 * to indicate that it is the primary key and its value is automatically generated.
 * The {@code getName} method is annotated with {@code @Column} to specify the
 * column name in the database and to enforce that the name cannot be null.
 * </p>
 * 
 * <p>
 * The {@code toString} method is overridden to provide a string representation
 * of the location object, including its id and name.
 * </p>
 * 
 * @author Melissa Ruiz
 */
@Entity
@Table(name = "locations")
public class Location {

    private int id;
    private String name;

    /**
     * Default constructor.
     * Creates a new {@code Location} object with no parameters.
     */
    public Location() {
    }

    /**
     * Constructor with the location name.
     * Creates a new {@code Location} object with the specified name.
     *
     * @param name the name of the location
     */
    public Location(String name) {
        this.name = name;
    }

    /**
     * Gets the unique identifier (ID) of the location.
     * 
     * @return the ID of the location
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier (ID) of the location.
     * 
     * @param id the new ID of the location
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the location.
     * 
     * @return the name of the location
     */
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the location.
     * 
     * @param name the new name of the location
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the location object, including its id and name.
     * 
     * @return a string representing the location
     */
    @Override
    public String toString() {
        return "Location [id=" + id + ", name=" + name + "]";
    }
}
