package es.iespuerto.mr.flora.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing a characteristic.
 * This class is mapped to the "characteristics" table in the database.
 * 
 * <p>Each characteristic has an ID and a description.</p>
 * 
 * <p>Annotations:</p>
 * <ul>
 *   <li>{@link Entity} - Specifies that the class is an entity, representing a table in the database.</li>
 *   <li>{@link Table} - Specifies the name of the table in the database to which the entity is mapped.</li>
 *   <li>{@link Id} - Specifies the primary key of the entity.</li>
 *   <li>{@link GeneratedValue} - Specifies the strategy used to generate the primary key value.</li>
 *   <li>{@link Column} - Specifies the mapping of the field to a column in the table.</li>
 * </ul>
 * 
 * <p>Constructors:</p>
 * <ul>
 *   <li>{@link #Characteristic()} - Default constructor for the {@code Characteristic} entity.</li>
 *   <li>{@link #Characteristic(String)} - Constructor that sets the description of the characteristic.</li>
 * </ul>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #getId()} - Returns the ID of the characteristic.</li>
 *   <li>{@link #setId(int)} - Sets the ID of the characteristic.</li>
 *   <li>{@link #getDescripcion()} - Returns the description of the characteristic.</li>
 *   <li>{@link #setDescripcion(String)} - Sets the description of the characteristic.</li>
 *   <li>{@link #toString()} - Returns a string representation of the characteristic.</li>
 * </ul>
 * 
 * @see jakarta.persistence.Entity
 * @see jakarta.persistence.Table
 * @see jakarta.persistence.Id
 * @see jakarta.persistence.GeneratedValue
 * @see jakarta.persistence.Column
 */
@Entity
@Table(name = "characteristics")
public class Characteristic {

    private int id;
    private String descripcion;

    /**
     * Default constructor for the {@code Characteristic} entity.
     */
    public Characteristic() {
    }

    /**
     * Constructor that sets the description of the characteristic.
     * 
     * @param descripcion The description of the characteristic.
     */
    public Characteristic(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Returns the ID of the characteristic.
     * 
     * @return The ID of the characteristic.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the characteristic.
     * 
     * @param id The ID of the characteristic.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the description of the characteristic.
     * 
     * @return The description of the characteristic.
     */
    @Column(name = "descripcion", nullable = false)
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the description of the characteristic.
     * 
     * @param descripcion The description of the characteristic.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Returns a string representation of the characteristic.
     * 
     * @return A string representation of the characteristic, including its ID and description.
     */
    @Override
    public String toString() {
        return "Characteristic [id=" + id + ", descripcion=" + descripcion + "]";
    }
}
