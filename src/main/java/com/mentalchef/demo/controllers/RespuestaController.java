package com.mentalchef.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private PreguntaDtoConverter preguntaDtoConverter;

    @GetMapping("")
    public List<Respuesta> getRespuestas() {
        return aplicacionRespuestas.getRespuestas();
    }

    @GetMapping("/preguntaId/{id}")
    public List<RespuestaDto> getRespuestasByPreguntaId(@PathVariable Long id) {
         
        List<Respuesta> listaRespuestas = aplicacionRespuestas.getRespuestasByPreguntaId(id);

        List<RespuestaDto> listaRespuestasDto = listaRespuestas.stream()
                .map(respuesta -> respuestaDtoConverter.convertToRespuestaDto(respuesta))
                .toList();
        
        return listaRespuestasDto;
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
    @Transactional
    public ResponseEntity<String> actualizarRespuestas(@PathVariable Long id,
            @RequestBody List<RespuestaDto> respuestasActualizadasDto) {
        // Buscar la pregunta existente por su ID
        Pregunta preguntaExistente = aplicacionPreguntas.getPregunta(id);

        if (preguntaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Convertir la pregunta existente a DTO para evitar la inicialización perezosa
        PreguntaDto preguntaDto = preguntaDtoConverter.convertToPreguntaDto(preguntaExistente);

        List<Respuesta> listaRespuestas = aplicacionRespuestas.getRespuestasByPreguntaId(id);

        List<RespuestaDto> listaRespuestasDto = listaRespuestas.stream()
                .map(respuesta -> respuestaDtoConverter.convertToRespuestaDto(respuesta))
                .toList();

        // Asignar las respuestas existentes a la pregunta DTO
        preguntaDto.setRespuestas(listaRespuestasDto);

        // Iterar sobre las respuestas actualizadas
        for (RespuestaDto respuestaActualizadaDto : respuestasActualizadasDto) {
            Respuesta respuestaActualizada = respuestaDtoConverter.convertToRespuestaWithoutUsuario(respuestaActualizadaDto);
            if (respuestaActualizada.getId() != null) {
                // Busca la respuesta en la pregunta existente usando su ID
                RespuestaDto respuestaExistenteDto = preguntaDto.getRespuestas().stream()
                        .filter(respuesta -> respuesta.getIdRespuesta().equals(respuestaActualizada.getId()))
                        .findFirst()
                        .orElse(null);

                if (respuestaExistenteDto != null) {
                    // Actualizar los campos necesarios
                    respuestaExistenteDto.setRespuesta(respuestaActualizada.getRespuesta());
                    respuestaExistenteDto.setCorrecta(respuestaActualizada.isCorrecta());
                    // Guardar la respuesta actualizada
                    aplicacionRespuestas.insertRespuesta(respuestaDtoConverter.convertToRespuesta(respuestaExistenteDto));
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
