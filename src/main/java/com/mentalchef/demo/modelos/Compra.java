package com.mentalchef.demo.modelos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "compra")
public class Compra {

    @EmbeddedId
    private CompraId id;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_compra", nullable = false)
    private Date fechaCompra;

    public Compra(CompraId id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
        this.fechaCompra = new Date();  // Se establece la fecha de compra en el momento de la creación
    }

    // Constructor alternativo si deseas personalizar la fecha
    public Compra(CompraId id, int cantidad, Date fechaCompra) {
        this.id = id;
        this.cantidad = cantidad;
        this.fechaCompra = fechaCompra;
    }
}
