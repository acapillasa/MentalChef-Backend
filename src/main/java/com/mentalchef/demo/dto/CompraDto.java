package com.mentalchef.demo.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompraDto {

    private CompraIdDto id;
    private Date fechaCompra;
    public CompraDto(CompraIdDto id) {
        this.id = id;
        this.fechaCompra = new Date();
    }

}
