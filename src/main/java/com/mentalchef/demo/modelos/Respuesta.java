package com.mentalchef.demo.modelos;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "respuesta")
public class Respuesta {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "respuesta")
    private String respuesta;

    @Column(name = "correcta")
    private boolean correcta;

    @ManyToOne
    @JoinColumn(name = "pregunta_id")
    private Pregunta pregunta;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    public Respuesta(String respuesta, boolean correcta, Pregunta pregunta, Usuario usuario) {
        this.respuesta = respuesta;
        this.correcta = correcta;
        this.pregunta = pregunta;
        this.usuario = usuario;
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
