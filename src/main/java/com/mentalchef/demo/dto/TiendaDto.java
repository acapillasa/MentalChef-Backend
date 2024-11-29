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
    private double coste;
    private double costeV;
    private String imagen;

    public TiendaDto(String nombre, String descripcion, double coste, double costeV, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.coste = coste;
        this.costeV = costeV;
        this.imagen = imagen;
    }
}
