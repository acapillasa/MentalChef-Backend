package com.mentalchef.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mentalchef.demo.aplicacion.IAplicacionPregunta;
import com.mentalchef.demo.dto.PreguntaDto;
import com.mentalchef.demo.dto.PreguntaDtoConverter;
import com.mentalchef.demo.modelos.Pregunta;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@AllArgsConstructor
@RequestMapping("/preguntas")
public class PreguntaController {

    IAplicacionPregunta aplicacionPregunta;

    PreguntaDtoConverter preguntaDtoConverter;

    @GetMapping("")
    public ResponseEntity<List<Pregunta>> getpreguntas() {
        List<Pregunta> preguntas = aplicacionPregunta.getPreguntas();
        return ResponseEntity.ok(preguntas);
    }

    @PostMapping("/insertar")
    public ResponseEntity<PreguntaDto> insertarPregunta(@RequestBody PreguntaDto preguntaDto) {
        Pregunta nuevaPregunta = aplicacionPregunta.insertPregunta(preguntaDtoConverter.convertToPregunta(preguntaDto));

        PreguntaDto resultadoDto = preguntaDtoConverter.convertToPreguntaDto(nuevaPregunta);

        return ResponseEntity.ok(resultadoDto);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Pregunta> actualizarPregunta(@PathVariable Long id, @RequestBody Pregunta preguntaActualizada) {
        Pregunta preguntaExistente = aplicacionPregunta.getPregunta(id);
        
        if (preguntaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        
        preguntaExistente.setPregunta(preguntaActualizada.getPregunta());
        preguntaExistente.setCuriosidad(preguntaActualizada.getCuriosidad());
        preguntaExistente.setDificultad(preguntaActualizada.getDificultad());
        preguntaExistente.setCategoria(preguntaActualizada.getCategoria());
        preguntaExistente.setRespuestas(preguntaActualizada.getRespuestas());
        preguntaExistente.setImagen(preguntaActualizada.getImagen());


        aplicacionPregunta.insertPregunta(preguntaExistente);
        
        return ResponseEntity.ok(preguntaExistente);
    }

    @DeleteMapping("eliminar/{id}")
    public String eliminarPregunta(int id) {
        try {
            aplicacionPregunta.deletePreguntaById(id);
            return "Pregunta eliminada con exito";
        } catch (Exception e) {
            return "Error al eliminar pregunta";
        }
    }

    @GetMapping("/diaria")
    public ResponseEntity<Pregunta> getPreguntaDiaria() {
        Pregunta preguntaDiaria = aplicacionPregunta.getPreguntaAlAzar();
        if (preguntaDiaria != null) {
            return ResponseEntity.ok(preguntaDiaria);
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
    public ResponseEntity<Pregunta> getPregunta(@PathVariable Long id) {
        Pregunta pregunta = aplicacionPregunta.getPregunta(id);
        if (pregunta != null) {
            return ResponseEntity.ok(pregunta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Pregunta>> getPreguntasPorCategoria(@PathVariable String categoria) {
        List<Pregunta> preguntas = aplicacionPregunta.getPreguntaByCategoria(categoria);
        return ResponseEntity.ok(preguntas);
    }
    

}
