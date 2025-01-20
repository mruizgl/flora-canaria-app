package es.iespuerto.mr.flora.model;


import jakarta.persistence.*;

@Entity
@Table(name = "plants")
public class Plant {

    private int id;
    private String commonName;
    private String scientificName;

    public Plant() {
    }

    public Plant(String commonName, String scientificName) {
        this.commonName = commonName;
        this.scientificName = scientificName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "common_name", nullable = false)
    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    @Column(name = "scientific_name", nullable = false)
    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    @Override
    public String toString() {
        return "Plant [id=" + id + ", commonName=" + commonName + ", scientificName=" + scientificName + "]";
    }
}
