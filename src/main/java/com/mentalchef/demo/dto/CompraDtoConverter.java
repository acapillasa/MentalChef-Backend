package com.mentalchef.demo.dto;

import com.mentalchef.demo.dto.userdtos.UserDtoConverter;
import com.mentalchef.demo.modelos.Compra;
import com.mentalchef.demo.modelos.CompraId;

public class CompraDtoConverter {

    CompraIdDtoConverter compraIdDtoConverter;

    UserDtoConverter userDtoConverter;

    TiendaDtoConverter tiendaDtoConverter;
    
    public CompraDto convertToCompraDto(Compra compra) {
        CompraDto compraDto = new CompraDto();
        compraDto.setId(new CompraIdDto(compraIdDtoConverter.convertToCompraIdDto(compra.getId())));
        compraDto.setFechaCompra(compra.getFechaCompra());
        return compraDto;
    }

    public Compra convertToCompra(CompraDto compraDto) {
        Compra compra = new Compra();
        CompraId compraId = new CompraId();
        compraId.setUsuario(userDtoConverter.toUsuario(compraDto.getId().getUsuario()));
        compraId.setTienda(tiendaDtoConverter.convertToTienda(compraDto.getId().getTienda()));
        compra.setId(compraId);
        compra.setFechaCompra(compraDto.getFechaCompra());
        return compra;
    }
}
