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
    private Long usuario;
    private List<RespuestaDto> respuestas;

    public PreguntaDto(String pregunta,String dificultad,String curiosidad,String imagen,String categoria, Long usuario) {
        this.pregunta = pregunta;
        this.dificultad = dificultad;
        this.curiosidad = curiosidad;
        this.imagen = imagen;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public PreguntaDto() {};

}
