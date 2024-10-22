package com.mentalchef.demo.controllers;

import java.util.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mentalchef.demo.aplicacion.IAplicacionCategorias;
import com.mentalchef.demo.dto.CategoriaDto;
import com.mentalchef.demo.dto.CategoriaDtoConverter;
import com.mentalchef.demo.modelos.Categoria;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/categorias")
public class CategoriaController {

    IAplicacionCategorias aplicacionCategorias;

    CategoriaDtoConverter categoriaDtoConverter;

    @GetMapping("")
    public List<CategoriaDto> getCategorias() {

        List<CategoriaDto> listaDto = new ArrayList<>();
        for (Categoria categoriaDto : aplicacionCategorias.getCategorias()) {

            listaDto.add(categoriaDtoConverter.convertToCategoriaDto(categoriaDto));
        }
        return listaDto;
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
