package com.mentalchef.demo.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Chef extends Usuario{
    
    @Column(name = "preguntasaprobadas")
    private int preguntasAprobadas;

    public Chef(String nombre, String descripcion, String email, String password, int monedaV, int preguntasAprobadas) {
        super(nombre, descripcion, email, password, monedaV);
        this.preguntasAprobadas = preguntasAprobadas;
    }

}
