package com.mentalchef.demo.dto;

import lombok.Data;

@Data
public class RespuestaDto {
    private Long idRespuesta;
    private String respuesta;
    private boolean correcta;
    private Long preguntaId;
    private Long usuarioId;

    public RespuestaDto(String respuesta, boolean correcta, Long preguntaId, Long usuarioId) {
        this.respuesta = respuesta;
        this.correcta = correcta;
        this.preguntaId = preguntaId;
        this.usuarioId = usuarioId;
    }

    public RespuestaDto(String respuesta, boolean correcta) {
        this.respuesta = respuesta;
        this.correcta = correcta;
    }

    public RespuestaDto(Long idRespuesta, String respuesta, boolean correcta) {
        this.idRespuesta = idRespuesta;
        this.respuesta = respuesta;
        this.correcta = correcta;
    }

    public RespuestaDto() {
    };
}
