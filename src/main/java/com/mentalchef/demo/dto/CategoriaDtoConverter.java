package com.mentalchef.demo.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mentalchef.demo.aplicacion.AplicacionCategorias;
import com.mentalchef.demo.modelos.Categoria;
import com.mentalchef.demo.modelos.Dificultad;
import com.mentalchef.demo.modelos.Pregunta;
import com.mentalchef.demo.modelos.Respuesta;

@Component
public class CategoriaDtoConverter {

    @Autowired
    private AplicacionCategorias aplicacionCategorias;

    public static CategoriaDto convertToCategoriaDto(Categoria categoria) {
        CategoriaDto categoriaDto = new CategoriaDto();

        categoriaDto.setCategoria(categoria.getCategoria());

        return categoriaDto;
    }

    public Categoria convertToCategoria(CategoriaDto categoriaDto) {
        Categoria categoria = new Categoria();

        categoria.setCategoria(categoriaDto.getCategoria());

        return categoria;
    }

}
