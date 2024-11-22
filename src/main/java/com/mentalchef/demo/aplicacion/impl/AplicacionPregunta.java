package com.mentalchef.demo.aplicacion.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mentalchef.demo.aplicacion.IAplicacionPregunta;
import com.mentalchef.demo.modelos.Pregunta;
import com.mentalchef.demo.persistencia.IPersistencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AplicacionPregunta implements IAplicacionPregunta {

    IPersistencia<Pregunta> persistencia;

    @Override
    @Transactional
    public Pregunta getPregunta(Long id) {
        try {
            return persistencia.obtener(id);
        } catch (Exception e) {
            System.err.println("Error al obtener la pregunta con id: " + id + " - " + e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public List<Pregunta> getPreguntas() {
        try {
            return persistencia.obtenerTodos();
        } catch (Exception e) {
            System.err.println("Error al obtener las preguntas - " + e.getMessage());
            return List.of();
        }
    }

    @Override
    @Transactional
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
    @Transactional
    public Pregunta getPreguntaByName(String Pregunta) {
        try {
            return persistencia.obtenerPreguntaPorNombre(Pregunta);
        } catch (Exception e) {
            System.err.println("Error al obtener preguntas por nombre: " + Pregunta + " - " + e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public boolean deletePreguntaById(Long id) {
        return persistencia.eliminarPreguntaPorId(id);
    }

    @Override
    @Transactional
    public Pregunta getPreguntaDiariaAlAzar() {
        return persistencia.obtenerPreguntaConRespuestasAleatoriaDiaria();
    }

    @Override
    @Transactional
    public List<Pregunta> getPreguntaByCategoria(String categoria) {
        try {
            return persistencia.obtenerPreguntasPorCategoria(categoria);
        } catch (Exception e) {
            System.err.println("Error al obtener preguntas por categoria: " + categoria + " - " + e.getMessage());
            return List.of();
        }
    }

    @Override
    @Transactional
    public Pregunta getPreguntaAlAzar() {
        return persistencia.obtenerPreguntaConRespuestasAleatoria();
    }

}
