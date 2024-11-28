package com.mentalchef.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TiendaDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Long coste;

    public TiendaDto(String nombre, String descripcion, Long coste) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.coste = coste;
    }
}
