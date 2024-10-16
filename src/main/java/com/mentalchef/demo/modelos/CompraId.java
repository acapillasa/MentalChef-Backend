package com.mentalchef.demo.modelos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class CompraId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tienda_id", nullable = false)
    private Tienda tienda;

    // Constructor con parámetros
    public CompraId(Usuario usuario, Tienda tienda) {
        this.usuario = usuario;
        this.tienda = tienda;
    }












    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompraId compraId = (CompraId) o;
        return usuario.equals(compraId.usuario) && tienda.equals(compraId.tienda);
    }

    @Override
    public int hashCode() {
        return usuario.hashCode() + tienda.hashCode();
    }
}
