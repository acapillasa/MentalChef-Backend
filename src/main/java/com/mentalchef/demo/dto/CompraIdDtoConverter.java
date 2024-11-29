package com.mentalchef.demo.dto;

import com.mentalchef.demo.dto.userdtos.UserDtoConverter;
import com.mentalchef.demo.modelos.CompraId;

public class CompraIdDtoConverter {

    private TiendaDtoConverter tiendaDtoConverter;

    private UserDtoConverter usuarioDtoConverter;
    
    public CompraIdDto convertToCompraIdDto (CompraId compraID) {
        CompraIdDto compraIdDto = new CompraIdDto();
        compraIdDto.setTienda(tiendaDtoConverter.convertToTiendaDto(compraID.getTienda()));
        compraIdDto.setUsuario(usuarioDtoConverter.toUserGetDto(compraID.getUsuario()));
        return compraIdDto;
    }
}
