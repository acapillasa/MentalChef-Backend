package com.mentalchef.demo.modelos;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class ProgresoId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "pregunta_id", nullable = false)
    private Pregunta pregunta;

    @Column(name = "intento")
    private int intento;

    // Constructor con parámetros
    public ProgresoId(Usuario usuario, Pregunta pregunta, int intento) {
        this.usuario = usuario;
        this.pregunta = pregunta;
        this.intento = intento;
    }
    





    
    // Hashcode y equals son necesarios para las claves compuestas
    @Override
    public int hashCode() {
        return usuario.hashCode() + pregunta.hashCode() + intento;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProgresoId that = (ProgresoId) obj;
        return this.intento == that.intento &&
               this.usuario.equals(that.usuario) &&
               this.pregunta.equals(that.pregunta);
    }
}
