package com.mentalchef.demo.aplicacion;

import java.util.List;

import com.mentalchef.demo.modelos.Categoria;

public interface IAplicacionCategorias {

    public Categoria getCategoria(Long id);

    public List<Categoria> getCategorias();

    public String insertCategoria(Categoria Categoria);

    public List<Categoria> getListCategoriaByName(String categoria);

    public void deleteCategoriaById(int id);

    public Categoria getCategoriaByName(String categoria);
}
