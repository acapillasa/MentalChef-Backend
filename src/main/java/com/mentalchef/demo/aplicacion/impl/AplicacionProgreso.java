package com.mentalchef.demo.aplicacion.impl;

import java.util.List;

import org.springframework.stereotype.Service;


import com.mentalchef.demo.aplicacion.IAplicacionProgreso;
import com.mentalchef.demo.modelos.Progreso;
import com.mentalchef.demo.persistencia.IPersistencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AplicacionProgreso implements IAplicacionProgreso {

    IPersistencia<Progreso> persistencia;

    @Override
    public Progreso getProgreso(Long id) {
        return persistencia.obtener(id);
    }

    @Override
    public List<Progreso> getProgresosUsuario(Long id) {
        return persistencia.obtenerProgresosUsuario(id);
    }

    @Override
    public List<Progreso> getProgresos() {
        return persistencia.obtenerTodos();
    }

    @Override
    public List<Progreso> getProgresosMes() {
        return persistencia.obtenerProgresosMes();
    }

    @Override
    public String insertProgreso(Progreso progreso) {
        persistencia.guardar(progreso);
        return "Progreso inserted successfully";
    }


    @Override
    public String updateProgreso(Progreso progreso) {
        persistencia.guardar(progreso);
        return "Progreso updated successfully";
    }

}
