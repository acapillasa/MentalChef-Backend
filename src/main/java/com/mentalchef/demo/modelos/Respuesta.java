package com.mentalchef.demo.modelos;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "respuesta_seq_gen")
    @SequenceGenerator(name = "respuesta_seq_gen", sequenceName = "respuesta_SEQ", allocationSize = 1)
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

    public Respuesta(String respuesta) {
        this.respuesta = respuesta;
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
