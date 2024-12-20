package com.mentalchef.demo.aplicacion.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mentalchef.demo.aplicacion.IAplicacionRespuestas;
import com.mentalchef.demo.modelos.Respuesta;
import com.mentalchef.demo.persistencia.IPersistencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AplicacionRespuestas implements IAplicacionRespuestas {

    IPersistencia<Respuesta> persistencia;

    @Override
    public Respuesta getRespuesta(Long id) {
        return persistencia.obtener(id);
    }

    @Override
    public List<Respuesta> getRespuestas() {
        return persistencia.obtenerTodos();
    }

    @Override
    public String insertRespuesta(Respuesta respuesta) {
        try {
            persistencia.guardar(respuesta);
            return "Respuesta insertada correctamente";
        } catch (Exception e) {
            System.err.println("Error al insertar la respuesta: " + e.getMessage());
            return "Error al insertar la respuesta: " + e.getMessage();
        }
    }

    @Override
    public List<Respuesta> getListRespuestaByName(String respuesta) {
        return persistencia.obtenerPorNombre(respuesta);
    }

    @Override
    public void deleteRespuestaById(int id) {
        persistencia.eliminar(persistencia.obtener(id));
    }

    @Override
    public void deleteRespuestasByPreguntaId(Long id) {
        persistencia.eliminarRespuestasDePregunta(id);
    }

    @Override
    public List<Respuesta> getRespuestasByPreguntaId(Long id) {
        return persistencia.obtenerRespuestasDePregunta(id);
    }

}
