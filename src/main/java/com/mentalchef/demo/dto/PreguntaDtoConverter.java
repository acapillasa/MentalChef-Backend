package com.mentalchef.demo.dto;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.mentalchef.demo.aplicacion.IAplicacionCategorias;
import com.mentalchef.demo.aplicacion.IAplicacionUsuarios;
import com.mentalchef.demo.modelos.Dificultad;
import com.mentalchef.demo.modelos.Pregunta;
import com.mentalchef.demo.modelos.Respuesta;

@Component
public class PreguntaDtoConverter {

    @Autowired
    private IAplicacionCategorias aplicacionCategorias;

    @Autowired
    private IAplicacionUsuarios aplicacionUsuarios;

    public Pregunta convertToPregunta(PreguntaDto preguntaDto) {
        Pregunta pregunta = new Pregunta();
        pregunta.setPregunta(preguntaDto.getPregunta());
        pregunta.setDificultad(Dificultad.valueOf(preguntaDto.getDificultad()));
        pregunta.setVerificado(preguntaDto.isVerificado());
        pregunta.setCuriosidad(preguntaDto.getCuriosidad());
        pregunta.setImagen(preguntaDto.getImagen());
        pregunta.setCategoria(aplicacionCategorias.getCategoriaByName(preguntaDto.getCategoria()));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        pregunta.setUsuario(aplicacionUsuarios.buscarPorNombre(authentication.getName()));

        // Convertir y agregar respuestas
        if (preguntaDto.getRespuestas() != null) {
            for (RespuestaDto respuestaDto : preguntaDto.getRespuestas()) {
                Respuesta respuesta = new Respuesta(respuestaDto.getRespuesta(), respuestaDto.isCorrecta());
                respuesta.setPregunta(pregunta); // Asociar la respuesta con la pregunta
                pregunta.addRespuesta(respuesta);
            }
        }

        return pregunta;
    }

    public PreguntaDto convertToPreguntaDto(Pregunta pregunta) {
        PreguntaDto preguntaDto = new PreguntaDto();
        preguntaDto.setId(pregunta.getId());
        preguntaDto.setPregunta(pregunta.getPregunta());
        preguntaDto.setDificultad(pregunta.getDificultad().name());
        preguntaDto.setVerificado(pregunta.isVerificado());
        preguntaDto.setCuriosidad(pregunta.getCuriosidad());
        preguntaDto.setImagen(pregunta.getImagen());
        preguntaDto.setCategoria(pregunta.getCategoria().getCategoria());
        preguntaDto.setUsuario(pregunta.getUsuario().getId());

        // Convertir y agregar respuestas
        preguntaDto.setRespuestas(pregunta.getRespuestas().stream()
                .map(respuesta -> new RespuestaDto(respuesta.getId(), respuesta.getRespuesta(), respuesta.isCorrecta()))
                .collect(Collectors.toList()));

        return preguntaDto;
    }

    public Pregunta convertToPreguntaWithoutRespuestas(PreguntaDto preguntaDto) {
        Pregunta pregunta = new Pregunta();
        pregunta.setPregunta(preguntaDto.getPregunta());
        pregunta.setDificultad(Dificultad.valueOf(preguntaDto.getDificultad()));
        pregunta.setVerificado(preguntaDto.isVerificado());
        pregunta.setCuriosidad(preguntaDto.getCuriosidad());
        pregunta.setImagen(preguntaDto.getImagen());
        pregunta.setCategoria(aplicacionCategorias.getCategoriaByName(preguntaDto.getCategoria()));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        pregunta.setUsuario(aplicacionUsuarios.buscarPorNombre(authentication.getName()));

        return pregunta;
    }
}
