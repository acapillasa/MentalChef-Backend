package com.mentalchef.demo.persistencia;

import java.util.List;

import com.mentalchef.demo.modelos.Categoria;
import com.mentalchef.demo.modelos.Comentario;
import com.mentalchef.demo.modelos.Pregunta;
import com.mentalchef.demo.modelos.Progreso;
import com.mentalchef.demo.modelos.Respuesta;
import com.mentalchef.demo.modelos.Usuario;

public interface IPersistencia<T> {

    public boolean guardar(T t);

    public T obtener(Object id);

    public boolean actualizar(T t);

    public boolean eliminar(T t);

    public List<T> obtenerTodos();

    public List<T> obtenerPorNombre(String nombre);

    public List<T> query(String key, Object value);

    public List<Comentario> obtenerComentariosPorPregunta(Long preguntaId);

    public List<Comentario> obtenerComentariosPorUsuario(Long usuarioId);

    public Pregunta obtenerPreguntaConRespuestasAleatoriaDiaria();

    public Pregunta obtenerPreguntaConRespuestasAleatoria();

    public List<Pregunta> obtenerPreguntasPorCategoria(String nombreCategoria);

    public Categoria obtenerCategoriaPorNombre(String categoria);

    public Pregunta obtenerPreguntaPorNombre(String nombrePregunta);

    public boolean eliminarRespuestasDePregunta(Long id);

    public boolean eliminarPreguntaPorId(Long preguntaId);

    public boolean eliminarCategoriaPorCategoria(String categoriaNombre);

    public List<Respuesta> obtenerRespuestasDePregunta(Long id);

    public Usuario buscarPorNombreConCompras(String username);

    public List<Progreso> obtenerProgresosMes();

    public List<Progreso> obtenerProgresosUsuario(Long id);

}
