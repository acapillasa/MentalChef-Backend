package com.mentalchef.demo.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Chef extends Usuario{
    
    @Column(name = "preguntasaprobadas")
    private int preguntasAprobadas;

    public Chef(String username, String descripcion, String email, String password, int monedaV, int preguntasAprobadas) {
        super(username, descripcion, email, password, monedaV);
        this.preguntasAprobadas = preguntasAprobadas;
    }

}
