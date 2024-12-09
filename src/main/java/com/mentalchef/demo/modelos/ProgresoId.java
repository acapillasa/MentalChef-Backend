package com.mentalchef.demo.modelos;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgresoId implements Serializable {

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pregunta_id", nullable = false)
    private Pregunta pregunta;

    // Constructor con parámetros
    public ProgresoId(Usuario usuario, Pregunta pregunta, int intento) {
        this.usuario = usuario;
        this.pregunta = pregunta;
    }
    
    
    // Hashcode y equals son necesarios para las claves compuestas
    @Override
    public int hashCode() {
        return usuario.hashCode() + pregunta.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProgresoId that = (ProgresoId) obj;
        return this.usuario.equals(that.usuario) &&
               this.pregunta.equals(that.pregunta);
    }
}
