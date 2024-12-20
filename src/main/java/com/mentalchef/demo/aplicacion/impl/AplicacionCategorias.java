package com.mentalchef.demo.aplicacion.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mentalchef.demo.aplicacion.IAplicacionCategorias;
import com.mentalchef.demo.modelos.Categoria;
import com.mentalchef.demo.persistencia.IPersistencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AplicacionCategorias implements IAplicacionCategorias {

    IPersistencia<Categoria> persistencia;

    @Override
    public Categoria getCategoria(Long id) {
        try {
            return persistencia.obtener(id);
        } catch (Exception e) {
            System.err.println("Error al obtener la categoría con id: " + id + " - " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Categoria> getCategorias() {
        try {
            return persistencia.obtenerTodos();
        } catch (Exception e) {
            System.err.println("Error al obtener las categorías - " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public String insertCategoria(Categoria categoria) {
        try {
            persistencia.guardar(categoria);
            return "Categoría insertada con éxito";
        } catch (Exception e) {
            System.err.println("Error al insertar la categoría: " + e.getMessage());
            return "Error al insertar la categoría: " + e.getMessage();
        }
    }

    @Override
    public List<Categoria> getListCategoriaByName(String categoria) {
        try {
            return persistencia.obtenerPorNombre(categoria);
        } catch (Exception e) {
            System.err.println("Error al obtener categorías por nombre: " + categoria + " - " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public void deleteCategoriaByCategoria(String categoria) {
        try {
            persistencia.eliminarCategoriaPorCategoria(categoria);
        } catch (Exception e) {
            System.err.println("Error al eliminar la categoría: " + categoria + " - " + e.getMessage());
        }
    }

    @Override
    public Categoria getCategoriaByName(String categoria) {
        return persistencia.obtenerCategoriaPorNombre(categoria);
    }

    @Override
    public String updateCategoria(Categoria categoria) {
        try {
            Categoria existingCategoria = persistencia.obtenerCategoriaPorNombre(categoria.getCategoria());
            if (existingCategoria != null) {
                existingCategoria.setCategoria(categoria.getCategoria());
                existingCategoria.setDescripcion(categoria.getDescripcion());
                
                persistencia.guardar(existingCategoria);
                return "Categoría actualizada con éxito";
            } else {
                return "Categoría no encontrada";
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar la categoría: " + e.getMessage());
            return "Error al actualizar la categoría: " + e.getMessage();
        }
    }

}
