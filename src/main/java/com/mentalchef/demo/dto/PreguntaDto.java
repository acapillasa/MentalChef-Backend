package com.mentalchef.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class PreguntaDto {

    private Long id;
    private String pregunta;
    private String dificultad;
    private boolean verificado;
    private String curiosidad;
    private String imagen;
    private String categoria;
    private List<RespuestaDto> respuestas;

}
