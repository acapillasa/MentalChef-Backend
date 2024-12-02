package com.mentalchef.demo.modelos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

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

    @Column(name = "purchase_id", nullable = false)
    private String purchaseId;

    // Constructor con parámetros
    public CompraId(Usuario usuario, Tienda tienda) {
        this.usuario = usuario;
        this.tienda = tienda;
        this.purchaseId = UUID.randomUUID().toString(); // Generate a unique ID for each purchase
    }

    @PrePersist
    private void ensureId() {
        if (this.purchaseId == null) {
            this.purchaseId = UUID.randomUUID().toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompraId compraId = (CompraId) o;
        return usuario.equals(compraId.usuario) && tienda.equals(compraId.tienda) && purchaseId.equals(compraId.purchaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, tienda, purchaseId);
    }

    @Override
    public String toString() {
        return "CompraId{" +
                "usuarioId=" + usuario.getId() +
                ", tiendaId=" + tienda.getId() +
                ", purchaseId=" + purchaseId +
                '}';
    }
}
