package com.mentalchef.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mentalchef.demo.aplicacion.IAplicacionCategorias;
import com.mentalchef.demo.modelos.Categoria;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/categorias")
public class CategoriaController {

    IAplicacionCategorias aplicacionCategorias;

    @GetMapping("")
    public List<Categoria> getCategorias() {
        return aplicacionCategorias.getCategorias();
    }

    @PostMapping("/insertar")
    public String insertarCategoria(Categoria categoria) {
        return aplicacionCategorias.insertCategoria(categoria);
    }

    @DeleteMapping("eliminar/{id}")
    public String eliminarCategoria(int id) {
        try {
            aplicacionCategorias.deleteCategoriaById(id);
            return "categoria eliminada con exito";
        } catch (Exception e) {
            return "Error al eleiminar categoria";
        }
    }

}
