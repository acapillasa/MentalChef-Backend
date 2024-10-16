package com.mentalchef.demo.persistencia;

import java.util.List;

import com.mentalchef.demo.modelos.Comentario;
import com.mentalchef.demo.modelos.Pregunta;

public interface IPersistencia<T> {

    public boolean guardar(T t);

    public T obtener(Object id);

    public boolean actualizar(T t);

    public boolean eliminar(T t);

    public List<T> obtenerTodos();

    public List<T> obtenerPorNombre(String nombre);

    public List<T> query(String key, String value);

    public List<Comentario> obtenerComentariosPorPregunta(Long preguntaId);

    public List<Comentario> obtenerComentariosPorUsuario(Long usuarioId);

    public Pregunta obtenerPreguntaConRespuestasAleatoria();
}
