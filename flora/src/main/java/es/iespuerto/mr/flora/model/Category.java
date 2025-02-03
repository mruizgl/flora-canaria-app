package es.iespuerto.mr.flora.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a category entity with an id and a name.
 * This class is mapped to the "categories" table in the database.
 * 
 * The {@code Category} class provides constructors to create a category with or without a name,
 * and getter and setter methods to access and modify the id and name properties.
 * 
 * The id property is annotated with {@code @Id} and {@code @GeneratedValue} to indicate that it is the primary key
 * and its value is automatically generated. The name property is annotated with {@code @Column} to specify
 * the column name in the database and to enforce that it cannot be null.
 * 
 * The {@code toString} method is overridden to provide a string representation of the category object.
 * 
 * Annotations used:
 * <ul>
 *   <li>{@code @Entity} - Specifies that the class is an entity and is mapped to a database table.</li>
 *   <li>{@code @Table} - Specifies the name of the database table to be used for mapping.</li>
 *   <li>{@code @Id} - Specifies the primary key of an entity.</li>
 *   <li>{@code @GeneratedValue} - Provides for the specification of generation strategies for the values of primary keys.</li>
 *   <li>{@code @Column} - Used to specify the mapped column for a persistent property or field.</li>
 * </ul>
 * 
 * Fields:
 * <ul>
 *   <li><strong>id</strong> - The unique identifier for the category.</li>
 *   <li><strong>name</strong> - The name of the category.</li>
 * </ul>
 * 
 * Constructors:
 * <ul>
 *   <li><strong>Category()</strong> - Default constructor.</li>
 *   <li><strong>Category(String name)</strong> - Constructor with the name parameter.</li>
 * </ul>
 * 
 * Methods:
 * <ul>
 *   <li><strong>getId()</strong> - Returns the id of the category.</li>
 *   <li><strong>setId(int id)</strong> - Sets the id of the category.</li>
 *   <li><strong>getName()</strong> - Returns the name of the category.</li>
 *   <li><strong>setName(String name)</strong> - Sets the name of the category.</li>
 *   <li><strong>toString()</strong> - Returns a string representation of the category.</li>
 * </ul>
 * 
 * @author Melissa Ruiz
 */
@Entity
@Table(name = "categories")
public class Category {

    private int id;
    private String name;

    /**
     * Default constructor.
     * Creates a new {@code Category} object with no parameters.
     */
    public Category() {
    }

    /**
     * Constructor with the category name.
     * Creates a new {@code Category} object with the specified name.
     *
     * @param name the name of the category
     */
    public Category(String name) {
        this.name = name;
    }

    /**
     * Gets the unique identifier (ID) of the category.
     * 
     * @return the ID of the category
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier (ID) of the category.
     * 
     * @param id the new ID of the category
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the category.
     * 
     * @return the name of the category
     */
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     * 
     * @param name the new name of the category
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the category object, including its id and name.
     * 
     * @return a string representing the category
     */
    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + "]";
    }
}
