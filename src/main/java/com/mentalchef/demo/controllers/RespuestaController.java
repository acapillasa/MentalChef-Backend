package com.mentalchef.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mentalchef.demo.aplicacion.IAplicacionPregunta;
import com.mentalchef.demo.aplicacion.IAplicacionRespuestas;
import com.mentalchef.demo.dto.PreguntaDto;
import com.mentalchef.demo.dto.PreguntaDtoConverter;
import com.mentalchef.demo.dto.RespuestaDto;
import com.mentalchef.demo.dto.RespuestaDtoConverter;
import com.mentalchef.demo.modelos.Pregunta;
import com.mentalchef.demo.modelos.Respuesta;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private IAplicacionRespuestas aplicacionRespuestas;

    @Autowired
    private IAplicacionPregunta aplicacionPreguntas;

    @Autowired
    private RespuestaDtoConverter respuestaDtoConverter;

    @GetMapping("")
    public List<Respuesta> getRespuestas() {
        return aplicacionRespuestas.getRespuestas();
    }

    @PostMapping("/insertar")
    public ResponseEntity<String> insertarRespuesta(@RequestBody RespuestaDto respuestaDto) {
        Respuesta respuesta = respuestaDtoConverter.convertToRespuesta(respuestaDto);
        aplicacionRespuestas.insertRespuesta(respuesta);
        return ResponseEntity.ok("Respuesta insertada con éxito");
    }

    @PostMapping("/insertarLista")
    public ResponseEntity<String> insertarListRespuesta(@RequestBody List<RespuestaDto> respuestasDto) {
        for (RespuestaDto respuestaDto : respuestasDto) {
            aplicacionRespuestas.insertRespuesta(respuestaDtoConverter.convertToRespuesta(respuestaDto));
        }
        return ResponseEntity.ok("Respuestas insertadas con éxito");
    }

    @PutMapping("/actualizarLista/{id}")
    public ResponseEntity<String> actualizarRespuestas(@PathVariable Long id,
            @RequestBody List<RespuestaDto> respuestasActualizadasDto) {
        // Buscar la pregunta existente por su ID
        Pregunta preguntaExistente = aplicacionPreguntas.getPregunta(id);

        if (preguntaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Iterar sobre las respuestas actualizadas
        for (RespuestaDto respuestaActualizadaDto : respuestasActualizadasDto) {
            Respuesta respuestaActualizada = respuestaDtoConverter.convertToRespuesta(respuestaActualizadaDto);
            if (respuestaActualizada.getId() != null) {
                // Busca la respuesta en la pregunta existente usando su ID
                Respuesta respuestaExistente = preguntaExistente.getRespuestas().stream()
                        .filter(respuesta -> respuesta.getId().equals(respuestaActualizada.getId()))
                        .findFirst()
                        .orElse(null);

                if (respuestaExistente != null) {
                    // Actualizar los campos necesarios
                    respuestaExistente.setRespuesta(respuestaActualizada.getRespuesta());
                    respuestaExistente.setCorrecta(respuestaActualizada.isCorrecta());
                    // Guardar la respuesta actualizada
                    aplicacionRespuestas.insertRespuesta(respuestaExistente);
                }
            }
        }

        return ResponseEntity.ok("Respuestas actualizadas con éxito");
    }

    @DeleteMapping("/eliminarPorPregunta/{id}")
    public ResponseEntity<String> eliminarRespuestasPorPregunta(@PathVariable Long id) {

        aplicacionRespuestas.deleteRespuestasByPreguntaId(id);

        return ResponseEntity.ok("Respuestas eliminadas con éxito");
    }
}
