package com.mentalchef.demo.controllers;

import java.util.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/categoriasConEvento")
    public List<CategoriaDto> getCategoriasConEvento() {
        List<CategoriaDto> listaDto = new ArrayList<>();
        for (Categoria categoria : aplicacionCategorias.getCategorias()) {
            if (categoria.getCategoria().startsWith("evento_")) {
                listaDto.add(categoriaDtoConverter.convertToCategoriaDto(categoria));
            }
        }
        return listaDto;
    }

    @GetMapping("/categoriasSinEvento")
    public List<CategoriaDto> getCategoriasSinEvento() {
        List<CategoriaDto> listaDto = new ArrayList<>();
        for (Categoria categoria : aplicacionCategorias.getCategorias()) {
            if (!categoria.getCategoria().startsWith("evento_")) {
                listaDto.add(categoriaDtoConverter.convertToCategoriaDto(categoria));
            }
        }
        return listaDto;
    }

    @GetMapping("/todas")
    public List<CategoriaDto> getTodasCategorias() {
        List<CategoriaDto> listaDto = new ArrayList<>();
        for (Categoria categoria : aplicacionCategorias.getCategorias()) {
            listaDto.add(categoriaDtoConverter.convertToCategoriaDto(categoria));
        }
        return listaDto;
    }

    @PostMapping("/insertar")
    public void insertarCategoria(@RequestBody CategoriaDto categoria) {
        aplicacionCategorias.insertCategoria(categoriaDtoConverter.convertToCategoria(categoria));
    }

    @DeleteMapping("eliminar/{categoria}")
    public String eliminarCategoria(@PathVariable String categoria) {
        try {
            aplicacionCategorias.deleteCategoriaByCategoria(categoria);
            return "categoria eliminada con exito";
        } catch (Exception e) {
            return "Error al eleiminar categoria";
        }
    }

}
