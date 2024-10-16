package com.mentalchef.demo.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Pinche extends Usuario{
    
    @Column(name = "preguntascreadas")
    private int preguntasCreadas;

    public Pinche(String nombre, String descripcion, String email, String password, int monedaV, int preguntasCreadas) {
        super(nombre, descripcion, email, password, monedaV);
        this.preguntasCreadas = preguntasCreadas;
    }

}
