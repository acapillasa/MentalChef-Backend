package com.mentalchef.demo.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
@NoArgsConstructor
@Table(name = "pregunta")
public class Pregunta {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "pregunta")
    private String pregunta;

    @Enumerated(EnumType.STRING)
    @Column(name = "dificultad")
    private Dificultad dificultad;

    @Column(name = "verificado")
    private boolean verificado;

    @Column(name = "curiosidad")
    private String curiosidad;

    @Column(name = "imagen")
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @JsonBackReference
    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Respuesta> respuestas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Pregunta(String pregunta, Dificultad dificultad, boolean verificado, String curiosidad, String imagen,
            Categoria categoria, Usuario usuario) {
        this.pregunta = pregunta;
        this.dificultad = dificultad;
        this.verificado = verificado;
        this.curiosidad = curiosidad;
        this.imagen = imagen;
        this.categoria = categoria;
        this.usuario = usuario;
    }


    public void addRespuesta(Respuesta respuesta) {
        respuestas.add(respuesta);
        respuesta.setPregunta(this);
    }

    public void removeRespuesta(Respuesta respuesta) {
        respuestas.remove(respuesta);
        respuesta.setPregunta(null);
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
