package com.mentalchef.demo.aplicacion;

import java.util.List;

import com.mentalchef.demo.modelos.Comentario;

public interface IAplicacionComentarios {
    public Comentario getComentario(Long id);

    public List<Comentario> getComentarios();

    public String insertComentario(Comentario comentario);

    public List<Comentario> getComentariosByPregunta(Long id);

    public List<Comentario> getComentariosByUsuario(Long id);
}
