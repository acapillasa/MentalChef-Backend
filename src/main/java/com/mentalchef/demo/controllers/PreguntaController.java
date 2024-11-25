package com.mentalchef.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mentalchef.demo.aplicacion.IAplicacionCategorias;
import com.mentalchef.demo.aplicacion.IAplicacionPregunta;
import com.mentalchef.demo.dto.PreguntaDto;
import com.mentalchef.demo.dto.PreguntaDtoConverter;
import com.mentalchef.demo.dto.RespuestaDto;
import com.mentalchef.demo.modelos.Dificultad;
import com.mentalchef.demo.modelos.Pregunta;
import com.mentalchef.demo.modelos.Respuesta;

@RestController
@RequestMapping("/preguntas")
public class PreguntaController {

    @Autowired
    private IAplicacionPregunta aplicacionPregunta;

    @Autowired
    private IAplicacionCategorias aplicacionCategorias;

    @Autowired
    private PreguntaDtoConverter preguntaDtoConverter;

    @GetMapping("")
    public ResponseEntity<List<Pregunta>> getPreguntas() {
        List<Pregunta> preguntas = aplicacionPregunta.getPreguntas();
        return ResponseEntity.ok(preguntas);
    }

    @PostMapping("/insertar")
    @Transactional
    public ResponseEntity<PreguntaDto> insertarPregunta(@RequestBody PreguntaDto preguntaDto) {
        // Convertir PreguntaDto a Pregunta sin respuestas
        Pregunta pregunta = preguntaDtoConverter.convertToPreguntaWithoutRespuestas(preguntaDto);
        
        // Insertar la pregunta y obtener la pregunta con ID generado
        Pregunta nuevaPregunta = aplicacionPregunta.insertPregunta(pregunta);
        
        // // Asociar respuestas con la pregunta y guardar las respuestas
        // if (preguntaDto.getRespuestas() != null) {
        //     for (RespuestaDto respuestaDto : preguntaDto.getRespuestas()) {
        //         Respuesta respuesta = new Respuesta(respuestaDto.getRespuesta(), respuestaDto.isCorrecta());
        //         respuesta.setPregunta(nuevaPregunta); // Asociar la respuesta con la pregunta
        //         nuevaPregunta.addRespuesta(respuesta);
        //     }
        //     aplicacionPregunta.insertPregunta(nuevaPregunta); // Guardar las respuestas
        // }

        // Recargar la entidad para asegurarse de que el ID y las fechas estén actualizados
        nuevaPregunta = aplicacionPregunta.getPreguntaByName(nuevaPregunta.getPregunta());

        PreguntaDto resultadoDto = preguntaDtoConverter.convertToPreguntaDto(nuevaPregunta);
        System.out.println("Pregunta insertada: " + resultadoDto.toString());
        return ResponseEntity.ok(resultadoDto);
    }

    @PatchMapping("/verificar/{id}")
    public ResponseEntity<Pregunta> verificarPregunta(@PathVariable Long id) {
        // Buscar la pregunta existente por su ID
        Pregunta preguntaExistente = aplicacionPregunta.getPregunta(id);

        if (preguntaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Verificar la pregunta
        preguntaExistente.setVerificado(true);

        aplicacionPregunta.insertPregunta(preguntaExistente);
        return ResponseEntity.ok(preguntaExistente);
    }

    @PatchMapping("/desverificar/{id}")
    public ResponseEntity<Pregunta> desverificarPregunta(@PathVariable Long id) {
        // Buscar la pregunta existente por su ID
        Pregunta preguntaExistente = aplicacionPregunta.getPregunta(id);

        if (preguntaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Desverificar la pregunta
        preguntaExistente.setVerificado(false);

        aplicacionPregunta.insertPregunta(preguntaExistente);
        return ResponseEntity.ok(preguntaExistente);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Pregunta> actualizarPregunta(@PathVariable Long id, @RequestBody PreguntaDto preguntaDto) {
        // Buscar la pregunta existente por su ID
        Pregunta preguntaExistente = aplicacionPregunta.getPregunta(id);

        if (preguntaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Actualizar los campos de la pregunta existente
        preguntaExistente.setPregunta(preguntaDto.getPregunta());
        preguntaExistente.setDificultad(Dificultad.valueOf(preguntaDto.getDificultad()));
        preguntaExistente.setVerificado(preguntaDto.isVerificado());
        preguntaExistente.setCuriosidad(preguntaDto.getCuriosidad());
        preguntaExistente.setImagen(preguntaDto.getImagen());
        preguntaExistente.setCategoria(aplicacionCategorias.getCategoriaByName(preguntaDto.getCategoria()));

        aplicacionPregunta.insertPregunta(preguntaExistente);
        return ResponseEntity.ok(preguntaExistente);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarPregunta(@PathVariable Long id) {
        try {
            aplicacionPregunta.deletePreguntaById(id);
            return "Pregunta eliminada con exito";
        } catch (Exception e) {
            return "Error al eliminar pregunta";
        }
    }

    @GetMapping("/diaria")
    public ResponseEntity<PreguntaDto> getPreguntaDiaria() {
        Pregunta preguntaDiaria = aplicacionPregunta.getPreguntaAlAzar();
        PreguntaDto preguntaDiariaDto = preguntaDtoConverter.convertToPreguntaDto(preguntaDiaria);

        if (preguntaDiaria != null) {
            return ResponseEntity.ok(preguntaDiariaDto);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/alAzar")
    public PreguntaDto getPreguntaAlAzar() {
        Pregunta pregunta = aplicacionPregunta.getPreguntaAlAzar();
        return preguntaDtoConverter.convertToPreguntaDto(pregunta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreguntaDto> getPregunta(@PathVariable Long id) {
        Pregunta pregunta = aplicacionPregunta.getPregunta(id);
        PreguntaDto preguntaDto = preguntaDtoConverter.convertToPreguntaDto(pregunta);
        if (pregunta != null) {
            return ResponseEntity.ok(preguntaDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<PreguntaDto>> getPreguntasPorCategoria(@PathVariable String categoria) {
        List<Pregunta> preguntas = aplicacionPregunta.getPreguntaByCategoria(categoria);
        List<PreguntaDto> preguntasDto = new ArrayList<>();
        for (Pregunta pregunta : preguntas) {
            preguntasDto.add(preguntaDtoConverter.convertToPreguntaDto(pregunta));
        }
        return ResponseEntity.ok(preguntasDto);
    }
}
