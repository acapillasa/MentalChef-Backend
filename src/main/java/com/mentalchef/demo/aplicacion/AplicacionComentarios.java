package com.mentalchef.demo.aplicacion;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mentalchef.demo.modelos.Comentario;
import com.mentalchef.demo.persistencia.IPersistencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AplicacionComentarios implements IAplicacionComentarios {

    IPersistencia<Comentario> persistencia;

    @Override
    public Comentario getComentario(Long id) {
        try {
            return persistencia.obtener(id);
        } catch (Exception e) {
            System.err.println("Error al obtener el comentario con id: " + id + " - " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Comentario> getComentarios() {
        try {
            return persistencia.obtenerTodos();
        } catch (Exception e) {
            System.err.println("Error al obtener los comentarios - " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public String insertComentario(Comentario comentario) {
        try {
            persistencia.guardar(comentario);
            return "Comentario insertado con éxito";
        } catch (Exception e) {
            System.err.println("Error al insertar el comentario: " + e.getMessage());
            return "Error al insertar el comentario: " + e.getMessage();
        }
    }

    @Override
    public List<Comentario> getComentariosByPregunta(Long preguntaId) {
        try {
            return persistencia.obtenerComentariosPorPregunta(preguntaId);
        } catch (Exception e) {
            System.err.println("Error al obtener los comentarios de la pregunta con id: " + preguntaId + " - " + e.getMessage());
            return List.of();
        }
    }

    public List<Comentario> getComentariosByUsuario(Long usuarioId) {
        try {
            return persistencia.obtenerComentariosPorUsuario(usuarioId);
        } catch (Exception e) {
            System.err.println("Error al obtener los comentarios del usuario con id: " + usuarioId + " - " + e.getMessage());
            return List.of();
        }
    }

}
