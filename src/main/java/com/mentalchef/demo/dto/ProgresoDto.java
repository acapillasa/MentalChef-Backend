
package com.mentalchef.demo.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProgresoDto {
    private Long usuarioId;
    private String usuarioNombre;
    private Long preguntaId;
    private Date fechaRespuesta;
    private boolean acertado;
    private Date fechaCreacion;
    private Date fechaActualizacion;
}