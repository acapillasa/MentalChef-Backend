package com.mentalchef.demo.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pinche extends Usuario{
    
    @Column(name = "preguntascreadas")
    private int preguntasCreadas;

    public Pinche(String username, String descripcion, String email, String password, int monedaV, int preguntasCreadas) {
        super(username, descripcion, email, password, monedaV);
        this.preguntasCreadas = preguntasCreadas;
    }

}
