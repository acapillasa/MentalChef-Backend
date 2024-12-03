package com.mentalchef.demo.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mentalchef.demo.dto.userdtos.UserDtoConverter;
import com.mentalchef.demo.modelos.CompraId;

@Component
public class CompraIdDtoConverter {

    @Autowired
    private TiendaDtoConverter tiendaDtoConverter;

    @Autowired
    @Qualifier("userDtoConverter")
    private UserDtoConverter usuarioDtoConverter;
    
    public CompraIdDto convertToCompraIdDto (CompraId compraID) {
        CompraIdDto compraIdDto = new CompraIdDto();
        compraIdDto.setTienda(tiendaDtoConverter.convertToTiendaDto(compraID.getTienda()));
        compraIdDto.setUsuario(usuarioDtoConverter.toUserGetDto(compraID.getUsuario()));
        compraIdDto.setPurchaseId(compraID.getPurchaseId());
        return compraIdDto;
    }
}
