package com.mentalchef.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mentalchef.demo.aplicacion.IAplicacionPregunta;
import com.mentalchef.demo.dto.PreguntaDto;
import com.mentalchef.demo.dto.PreguntaDtoConverter;
import com.mentalchef.demo.modelos.Pregunta;

import lombok.AllArgsConstructor;



@RestController
@AllArgsConstructor
@RequestMapping("/preguntas")
public class PreguntaController {

    IAplicacionPregunta aplicacionPregunta;

    PreguntaDtoConverter preguntaDtoConverter;

    @GetMapping("")
    public List<Pregunta> getpreguntas() {
        return aplicacionPregunta.getPreguntas();
    }

    @PostMapping("/insertar")
    public String insertarPregunta(Pregunta pregunta) {
        return aplicacionPregunta.insertPregunta(pregunta);
    }

    @DeleteMapping("eliminar/{id}")
    public String eliminarPregunta(int id) {
        try {
            aplicacionPregunta.deletePreguntaById(id);
            return "Pregunta eliminada con exito";
        } catch (Exception e) {
            return "Error al eleiminar pregunta";
        }
    }

    @GetMapping("/diaria")
    public Pregunta getPreguntadiaria() {
        return aplicacionPregunta.getPreguntaDiariaAlAzar();
    }

    @GetMapping("/alAzar")
    public PreguntaDto getPreguntaAlAzar() {
        Pregunta pregunta = aplicacionPregunta.getPreguntaAlAzar();
        return preguntaDtoConverter.convertToPreguntaDto(pregunta);
    }
    

}
