package com.mentalchef.demo.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mentalchef.demo.dto.userdtos.UserDtoConverter;
import com.mentalchef.demo.modelos.Compra;
import com.mentalchef.demo.modelos.CompraId;

@Component
public class CompraDtoConverter {

    @Autowired
    CompraIdDtoConverter compraIdDtoConverter;

    UserDtoConverter userDtoConverter;

    @Autowired
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
