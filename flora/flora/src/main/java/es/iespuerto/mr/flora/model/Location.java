package es.iespuerto.mr.flora.model;

import jakarta.persistence.*;

@Entity
@Table(name = "locations")
public class Location {

    private int id;
    private String name;

    public Location() {
    }

    public Location(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location [id=" + id + ", name=" + name + "]";
    }
}
