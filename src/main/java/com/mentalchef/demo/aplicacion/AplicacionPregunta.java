package com.mentalchef.demo.aplicacion;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mentalchef.demo.modelos.Pregunta;
import com.mentalchef.demo.persistencia.IPersistencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AplicacionPregunta implements IAplicacionPregunta {

    IPersistencia<Pregunta> persistencia;

    @Override
    public Pregunta getPregunta(Long id) {
        try {
            return persistencia.obtener(id);
        } catch (Exception e) {
            System.err.println("Error al obtener la pregunta con id: " + id + " - " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Pregunta> getPreguntas() {
        try {
            return persistencia.obtenerTodos();
        } catch (Exception e) {
            System.err.println("Error al obtener las preguntas - " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public Pregunta insertPregunta(Pregunta pregunta) {
        try {
            persistencia.guardar(pregunta);
            return pregunta;
        } catch (Exception e) {
            System.err.println("Error al insertar la pregunta: " + e.getMessage());
            return new Pregunta();
        }
    }

    @Override
    public List<Pregunta> getPreguntaByName(String Pregunta) {
        try {
            return persistencia.obtenerPorNombre(Pregunta);
        } catch (Exception e) {
            System.err.println("Error al obtener preguntas por nombre: " + Pregunta + " - " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public void deletePreguntaById(int id) {
        persistencia.eliminar(persistencia.obtener(id));
    }

    @Override
    public Pregunta getPreguntaDiariaAlAzar() {
        return persistencia.obtenerPreguntaConRespuestasAleatoriaDiaria();
    }

    @Override
    public List<Pregunta> getPreguntaByCategoria(String categoria) {
        return persistencia.obtenerPreguntasPorCategoria(categoria);
    }

    @Override
    public Pregunta getPreguntaAlAzar() {
        return persistencia.obtenerPreguntaConRespuestasAleatoria();
    }

}
