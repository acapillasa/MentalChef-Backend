package com.mentalchef.demo.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mentalchef.demo.aplicacion.AplicacionCategorias;
import com.mentalchef.demo.modelos.Dificultad;
import com.mentalchef.demo.modelos.Pregunta;
import com.mentalchef.demo.modelos.Respuesta;

@Component
public class PreguntaDtoConverter {

    @Autowired
    private AplicacionCategorias aplicacionCategorias;

    public static PreguntaDto convertToPreguntaDto(Pregunta pregunta) {
        PreguntaDto preguntaDto = new PreguntaDto();
        preguntaDto.setId(pregunta.getId());
        preguntaDto.setPregunta(pregunta.getPregunta());
        preguntaDto.setDificultad(pregunta.getDificultad().name());
        preguntaDto.setVerificado(pregunta.isVerificado());
        preguntaDto.setCuriosidad(pregunta.getCuriosidad());
        preguntaDto.setImagen(pregunta.getImagen());
        preguntaDto.setCategoria(pregunta.getCategoria().getCategoria());

        List<RespuestaDto> respuestasDto = pregunta.getRespuestas().stream()
                .map(respuesta -> {
                    RespuestaDto respuestaDto = new RespuestaDto();
                    respuestaDto.setRespuesta(respuesta.getRespuesta());
                    respuestaDto.setCorrecta(respuesta.isCorrecta());
                    return respuestaDto;
                })
                .collect(Collectors.toList());

        preguntaDto.setRespuestas(respuestasDto);

        return preguntaDto;
    }

    public Pregunta convertToPregunta(PreguntaDto preguntaDto) {
        Pregunta pregunta = new Pregunta();
        pregunta.setId(preguntaDto.getId());
        pregunta.setPregunta(preguntaDto.getPregunta());
        pregunta.setDificultad(Dificultad.valueOf(preguntaDto.getDificultad()));
        pregunta.setVerificado(preguntaDto.isVerificado());
        pregunta.setCuriosidad(preguntaDto.getCuriosidad());
        pregunta.setImagen(preguntaDto.getImagen());
        pregunta.setCategoria(aplicacionCategorias.getCategoriaByName(preguntaDto.getCategoria()));

        List<Respuesta> respuestas = preguntaDto.getRespuestas().stream()
                .map(respuestaDto -> {
                    Respuesta respuesta = new Respuesta();
                    respuesta.setRespuesta(respuestaDto.getRespuesta());
                    respuesta.setCorrecta(respuestaDto.isCorrecta());
                    respuesta.setPregunta(pregunta);
                    return respuesta;
                })
                .collect(Collectors.toList());

        pregunta.setRespuestas(respuestas);

        return pregunta;
    }

}
