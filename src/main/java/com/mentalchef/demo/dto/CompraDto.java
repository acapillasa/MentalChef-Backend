package com.mentalchef.demo.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompraDto {

    private CompraIdDto id;
    private int cantidad;
    private Date fechaCompra;

    public CompraDto(CompraIdDto id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
        this.fechaCompra = new Date();  // Se establece la fecha de compra en el momento de la creación
    }

}
