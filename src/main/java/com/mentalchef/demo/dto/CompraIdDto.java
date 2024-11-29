package com.mentalchef.demo.dto;

import com.mentalchef.demo.dto.userdtos.UserGetDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompraIdDto {

    private UserGetDto usuario;
    private TiendaDto tienda;

    public CompraIdDto(UserGetDto usuario, TiendaDto tienda) {
        this.usuario = usuario;
        this.tienda = tienda;
    }

    public CompraIdDto(CompraIdDto compraIdDto) {
        this.usuario = compraIdDto.getUsuario();
        this.tienda = compraIdDto.getTienda();
    }
    
}
