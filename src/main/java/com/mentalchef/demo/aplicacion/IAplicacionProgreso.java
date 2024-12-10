package com.mentalchef.demo.aplicacion;

import java.util.List;

import com.mentalchef.demo.modelos.Progreso;

public interface IAplicacionProgreso {

    public Progreso getProgreso(Long id);

    public List<Progreso> getProgresosUsuario(Long id);

    public List<Progreso> getProgresos();

    public List<Progreso> getProgresosMes();

    public String insertProgreso(Progreso progreso);

    public String updateProgreso(Progreso progreso);
}
