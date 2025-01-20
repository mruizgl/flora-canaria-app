package es.iespuerto.mr.flora.model;

import jakarta.persistence.*;

@Entity
@Table(name = "favorite_plants")
public class Favorite {
    private int id;
    private int userId;
    private int plantId;

    public Favorite() {
    }

    public Favorite(int userId, int plantId) {
        this.userId = userId;
        this.plantId = plantId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "plant_id", nullable = false)
    public int getPlantId() {
        return plantId;
    }
    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    @Override
    public String toString() {
        return "FavoritePlant [id=" + id + ", userId=" + userId + ", plantId=" + plantId + "]";
    }
}
