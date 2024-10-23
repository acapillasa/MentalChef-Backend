package com.mentalchef.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mentalchef.demo.aplicacion.IAplicacionRespuestas;
import com.mentalchef.demo.dto.PreguntaDto;
import com.mentalchef.demo.dto.PreguntaDtoConverter;
import com.mentalchef.demo.dto.RespuestaDto;
import com.mentalchef.demo.dto.RespuestaDtoConverter;
import com.mentalchef.demo.modelos.Pregunta;
import com.mentalchef.demo.modelos.Respuesta;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/respuestas")
public class RespuestaController {

    IAplicacionRespuestas aplicacionRespuestas;

    RespuestaDtoConverter respuestaDtoConverter;

    @GetMapping("")
    public List<Respuesta> getRespuestas() {
        return aplicacionRespuestas.getRespuestas();
    }

    @PostMapping("/insertar")
    public ResponseEntity<String> insertarRespuesta(@RequestBody List<RespuestaDto> respuestasDto) {
        for (RespuestaDto respuestaDto : respuestasDto) {
            aplicacionRespuestas.insertRespuesta(respuestaDtoConverter.convertToRespuesta(respuestaDto));
        }
        return ResponseEntity.ok("Respuestas insertadas con éxito");
    }

    @DeleteMapping("eliminar/{id}")
    public String eliminarRespuesta(int id) {
        try {
            aplicacionRespuestas.deleteRespuestaById(id);
            return "Respuesta eliminada con exito";
        } catch (Exception e) {
            return "Error al eliminar respuesta";
        }
    }
}
