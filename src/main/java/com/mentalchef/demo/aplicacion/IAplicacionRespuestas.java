package com.mentalchef.demo.aplicacion;

import java.util.List;

import com.mentalchef.demo.modelos.Respuesta;

public interface IAplicacionRespuestas {

    public Respuesta getRespuesta(Long id);

    public List<Respuesta> getRespuestas();

    public String insertRespuesta(Respuesta Respuesta);

    public List<Respuesta> getListRespuestaByName(String Respuesta);

    public void deleteRespuestaById(int id);

    public void deleteRespuestasByPreguntaId(Long id);

    public List<Respuesta> getRespuestasByPreguntaId(Long id);
}
