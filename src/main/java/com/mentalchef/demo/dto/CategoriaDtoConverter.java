package com.mentalchef.demo.dto;

import org.springframework.stereotype.Component;

import com.mentalchef.demo.modelos.Categoria;

@Component
public class CategoriaDtoConverter {

    public static CategoriaDto convertToCategoriaDto(Categoria categoria) {
        CategoriaDto categoriaDto = new CategoriaDto();

        categoriaDto.setCategoria(categoria.getCategoria());
        categoriaDto.setDescripcion(categoria.getDescripcion());

        return categoriaDto;
    }

    public Categoria convertToCategoria(CategoriaDto categoriaDto) {
        Categoria categoria = new Categoria();

        categoria.setCategoria(categoriaDto.getCategoria());
        categoria.setDescripcion(categoriaDto.getDescripcion());

        return categoria;
    }

}
