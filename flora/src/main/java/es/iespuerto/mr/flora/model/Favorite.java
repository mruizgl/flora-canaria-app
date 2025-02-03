package es.iespuerto.mr.flora.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing a favorite plant for a user.
 * This class is mapped to the "favorite_plants" table in the database.
 * It is used to store the relationship between a user and their favorite plant.
 * 
 * <p>The class is annotated with {@code @Entity} to indicate that it is a JPA entity and is mapped to the
 * "favorite_plants" table in the database.</p>
 * 
 * <p>Fields:</p>
 * <ul>
 *   <li>{@code id} - The unique identifier for the favorite entry.</li>
 *   <li>{@code userId} - The ID of the user who marked the plant as a favorite.</li>
 *   <li>{@code plantId} - The ID of the plant that is marked as a favorite.</li>
 * </ul>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@code getId()} - Returns the unique identifier of the favorite entry.</li>
 *   <li>{@code setId(int id)} - Sets the unique identifier for the favorite entry.</li>
 *   <li>{@code getUserId()} - Returns the ID of the user associated with this favorite.</li>
 *   <li>{@code setUserId(int userId)} - Sets the ID of the user.</li>
 *   <li>{@code getPlantId()} - Returns the ID of the plant associated with this favorite.</li>
 *   <li>{@code setPlantId(int plantId)} - Sets the ID of the plant.</li>
 *   <li>{@code toString()} - Returns a string representation of the favorite plant entry.</li>
 * </ul>
 * 
 * <p>Annotations:</p>
 * <ul>
 *   <li>{@code @Entity}: Specifies that this class is a JPA entity.</li>
 *   <li>{@code @Table(name = "favorite_plants")}: Specifies the table to which this entity is mapped.</li>
 *   <li>{@code @Id}: Marks the {@code id} field as the primary key.</li>
 *   <li>{@code @GeneratedValue(strategy = GenerationType.AUTO)}: Indicates that the {@code id} should be auto-generated.</li>
 *   <li>{@code @Column(name = "user_id", nullable = false)}: Specifies the column for the {@code userId} field, ensuring it is not nullable.</li>
 *   <li>{@code @Column(name = "plant_id", nullable = false)}: Specifies the column for the {@code plantId} field, ensuring it is not nullable.</li>
 * </ul>
 */
@Entity
@Table(name = "favorite_plants")
public class Favorite {
    private int id;
    private int userId;
    private int plantId;

    /**
     * Default constructor for the Favorite class.
     */
    public Favorite() {
    }

    /**
     * Constructor for creating a Favorite entry with specified userId and plantId.
     * 
     * @param userId The ID of the user who marked the plant as a favorite.
     * @param plantId The ID of the plant that is marked as a favorite.
     */
    public Favorite(int userId, int plantId) {
        this.userId = userId;
        this.plantId = plantId;
    }

    /**
     * Gets the unique identifier of the favorite entry.
     * 
     * @return The unique identifier of the favorite.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the favorite entry.
     * 
     * @param id The unique identifier to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the user who marked the plant as a favorite.
     * 
     * @return The user ID associated with this favorite.
     */
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user who marked the plant as a favorite.
     * 
     * @param userId The ID of the user to be set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the ID of the plant that is marked as a favorite.
     * 
     * @return The plant ID associated with this favorite.
     */
    @Column(name = "plant_id", nullable = false)
    public int getPlantId() {
        return plantId;
    }

    /**
     * Sets the ID of the plant that is marked as a favorite.
     * 
     * @param plantId The ID of the plant to be set.
     */
    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    /**
     * Returns a string representation of the favorite plant entry.
     * 
     * @return A string representing the favorite plant entry.
     */
    @Override
    public String toString() {
        return "FavoritePlant [id=" + id + ", userId=" + userId + ", plantId=" + plantId + "]";
    }
}
