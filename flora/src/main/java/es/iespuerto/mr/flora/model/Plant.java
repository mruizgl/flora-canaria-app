package es.iespuerto.mr.flora.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a plant entity with common and scientific names.
 * This class is mapped to the "plants" table in the database.
 * 
 * It includes the following fields:
 * <ul>
 *   <li>id - The unique identifier for the plant.</li>
 *   <li>commonName - The common name of the plant.</li>
 *   <li>scientificName - The scientific name of the plant.</li>
 * </ul>
 * 
 * It provides constructors for creating a plant instance with or without initial values,
 * getter and setter methods for accessing and modifying the fields, and a toString method
 * for generating a string representation of the plant.
 * 
 * Annotations are used to specify the entity and table mapping, as well as column constraints.
 * 
 * Example usage:
 * <pre>
 *     Plant plant = new Plant("Rose", "Rosa");
 *     plant.setId(1);
 *     System.out.println(plant);
 * </pre>
 * 
 * Annotations:
 * <ul>
 *   <li>@Entity - Specifies that the class is an entity and is mapped to a database table.</li>
 *   <li>@Table (name = "plants") - Specifies the name of the database table to be used for mapping.</li>
 *   <li>@Id - Specifies the primary key of an entity.</li>
 *   <li>@GeneratedValue(strategy = GenerationType.AUTO) - Provides for the specification of generation strategies for the values of primary keys.</li>
 *   <li>@Column(name = "common_name", nullable = false) - Specifies the mapped column for a persistent property or field.</li>
 *   <li>@Column(name = "scientific_name", nullable = false) - Specifies the mapped column for a persistent property or field.</li>
 * </ul>
 */
@Entity
@Table(name = "plants")
public class Plant {

    private int id;
    private String commonName;
    private String scientificName;

    /**
     * Default constructor for creating a new instance of Plant with no initial values.
     */
    public Plant() {
    }

    /**
     * Constructor with parameters to initialize a plant with a common name and scientific name.
     *
     * @param commonName The common name of the plant.
     * @param scientificName The scientific name of the plant.
     */
    public Plant(String commonName, String scientificName) {
        this.commonName = commonName;
        this.scientificName = scientificName;
    }

    /**
     * Gets the unique identifier (ID) of the plant.
     * 
     * @return The ID of the plant.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier (ID) of the plant.
     * 
     * @param id The ID of the plant to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the common name of the plant.
     * 
     * @return The common name of the plant.
     */
    @Column(name = "common_name", nullable = false)
    public String getCommonName() {
        return commonName;
    }

    /**
     * Sets the common name of the plant.
     * 
     * @param commonName The common name to set.
     */
    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    /**
     * Gets the scientific name of the plant.
     * 
     * @return The scientific name of the plant.
     */
    @Column(name = "scientific_name", nullable = false)
    public String getScientificName() {
        return scientificName;
    }

    /**
     * Sets the scientific name of the plant.
     * 
     * @param scientificName The scientific name to set.
     */
    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    /**
     * Returns a string representation of the plant object, including its id, common name,
     * and scientific name.
     * 
     * @return A string representing the plant.
     */
    @Override
    public String toString() {
        return "Plant [id=" + id + ", commonName=" + commonName + ", scientificName=" + scientificName + "]";
    }
}
