package es.iespuerto.mr.flora.model;

import jakarta.persistence.*;

@Entity
@Table(name = "characteristics")
public class Characteristic {

    private int id;
    private String descripcion;

    public Characteristic() {
    }

    public Characteristic(String descripcion) {
        this.descripcion = descripcion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "descripcion", nullable = false)
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Characteristic [id=" + id + ", descripcion=" + descripcion + "]";
    }

}
