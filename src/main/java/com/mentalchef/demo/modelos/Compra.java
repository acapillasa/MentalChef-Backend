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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_compra", nullable = false)
    private Date fechaCompra;

    public Compra(CompraId id) {
        this.id = id;
        this.fechaCompra = new Date();  // Se establece la fecha de compra en el momento de la creación
    }

    // Constructor alternativo si deseas personalizar la fecha
    public Compra(CompraId id, Date fechaCompra) {
        this.id = id;
        this.fechaCompra = fechaCompra;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "compraId=" + id +
                ", fechaCompra=" + fechaCompra +
                '}';
    }
}
