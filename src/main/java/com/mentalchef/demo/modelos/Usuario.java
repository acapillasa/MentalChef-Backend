package com.mentalchef.demo.modelos;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "monedaV")
    private int monedaV;

    @OneToMany(mappedBy = "id.usuario", cascade = CascadeType.ALL)
    private List<Compra> compras;

    public Usuario(String nombre, String descripcion, String email, String password, int monedaV) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.email = email;
        this.password = password;
        this.monedaV = monedaV;
    }




    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechacreacion", updatable = false)
    private Date fechaCreacion;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaactualizacion")
    private Date fechaActualizacion;
}
