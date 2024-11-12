package com.mentalchef.demo.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mentalchef.demo.aplicacion.AplicacionPregunta;
import com.mentalchef.demo.aplicacion.AplicacionUsuarios;
import com.mentalchef.demo.modelos.Respuesta;

@Component
public class RespuestaDtoConverter {

    @Autowired
    private AplicacionPregunta aplicacionPregunta;

    @Autowired
    private AplicacionUsuarios aplicacionUsuarios;

    // Convierte una entidad Respuesta a RespuestaDto
    public static RespuestaDto convertToRespuestaDto(Respuesta respuesta) {
        RespuestaDto respuestaDto = new RespuestaDto();

        // Mapea los campos de Respuesta a RespuestaDto
        respuestaDto.setIdRespuesta(respuesta.getId());
        respuestaDto.setRespuesta(respuesta.getRespuesta());
        respuestaDto.setCorrecta(respuesta.isCorrecta());
        respuestaDto.setPreguntaId(respuesta.getPregunta().getId());
        respuestaDto.setUsuarioId(respuesta.getUsuario().getId());

        return respuestaDto;
    }

    // Convierte un RespuestaDto a una entidad Respuesta
    public Respuesta convertToRespuesta(RespuestaDto respuestaDto) {
        Respuesta respuesta = new Respuesta();

        respuesta.setId(respuestaDto.getIdRespuesta());
        respuesta.setRespuesta(respuestaDto.getRespuesta());
        respuesta.setCorrecta(respuestaDto.isCorrecta());
        respuesta.setPregunta(aplicacionPregunta.getPregunta(respuestaDto.getPreguntaId()));
        respuesta.setUsuario(aplicacionUsuarios.getUsuario(respuestaDto.getUsuarioId()));

        return respuesta;
    }
}
