package com.mentalchef.demo.aplicacion;

import java.util.List;

import com.mentalchef.demo.modelos.Pregunta;

public interface IAplicacionPregunta {

    public Pregunta getPregunta(Long id);

    public List<Pregunta> getPreguntas();

    public Pregunta insertPregunta(Pregunta pregunta);

    public Pregunta getPreguntaByName(String pregunta);

    public Pregunta getPreguntaDiariaAlAzar();

    public Pregunta getPreguntaAlAzar();

    public List<Pregunta> getPreguntaByCategoria(String categoria);

    public boolean deletePreguntaById(Long id);
}
