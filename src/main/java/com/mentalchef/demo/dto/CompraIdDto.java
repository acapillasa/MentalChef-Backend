package com.mentalchef.demo.dto;

import com.mentalchef.demo.dto.userdtos.UserGetDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompraIdDto {

    private UserGetDto usuario;
    private TiendaDto tienda;
    private String purchaseId;

    public CompraIdDto(UserGetDto usuario, TiendaDto tienda, String purchaseId) {
        this.usuario = usuario;
        this.tienda = tienda;
        this.purchaseId = purchaseId;
    }

    public CompraIdDto(CompraIdDto compraIdDto) {
        this.usuario = compraIdDto.getUsuario();
        this.tienda = compraIdDto.getTienda();
        this.purchaseId = compraIdDto.getPurchaseId();
    }
    
}
