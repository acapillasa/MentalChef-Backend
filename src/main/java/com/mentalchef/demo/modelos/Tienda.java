package com.mentalchef.demo.modelos;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tienda")
public class Tienda {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "coste")
    private double coste;

    @Column(name = "costeV")
    private double costeV;

    @Column(name = "imagen")
    private String imagen;

    @OneToMany(mappedBy = "id.tienda", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Compra> compras;

    public Tienda(String nombre, String descripcion, double coste) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.coste = coste;
    }

    public Tienda(String nombre, String descripcion, double coste, double costeV) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.coste = coste;
        this.costeV = costeV;
    }

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechacreacion", updatable = false)
    private Date fechaCreacion;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaactualizacion")
    private Date fechaActualizacion;

    @Override
    public String toString() {
        return "Tienda{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", costeV=" + costeV +
                '}';
    }
}
