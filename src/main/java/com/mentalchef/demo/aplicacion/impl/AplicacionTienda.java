package com.mentalchef.demo.aplicacion.impl;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mentalchef.demo.aplicacion.IAplicacionTienda;
import com.mentalchef.demo.aplicacion.IAplicacionUsuarios;
import com.mentalchef.demo.modelos.Compra;
import com.mentalchef.demo.modelos.CompraId;
import com.mentalchef.demo.modelos.Tienda;
import com.mentalchef.demo.modelos.Usuario;
import com.mentalchef.demo.persistencia.IPersistencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AplicacionTienda implements IAplicacionTienda {

    IPersistencia<Tienda> persistencia;

    IAplicacionUsuarios aplicacionUsuarios;

    @Override
    public Tienda getProducto(@PathVariable Long id) {
        return persistencia.obtener(id);
    }

    @Override
    public List<Tienda> getProductos() {
        return persistencia.obtenerTodos();
    }

    @Override
    public String insertProducto(@RequestBody Tienda producto) {
        persistencia.guardar(producto);
        return "Producto insertado con éxito";
    }

    @Override
    public void insertCompra(Compra compra) {
        Tienda producto = persistencia.obtener(compra.getId().getTienda().getId());
        producto.getCompras().add(compra);
        persistencia.guardar(producto);
    }

    @Override
    public List<Tienda> getListProdcutoByName(@RequestParam String nombreTienda) {
        return persistencia.obtenerPorNombre(nombreTienda);
    }

    @Override
    public void deleteProductoById(@PathVariable Long id) {
        persistencia.eliminar(persistencia.obtener(id));
    }

    @Override
    public Tienda getProdcutoByName(@RequestParam String nombreTienda) {
        return persistencia.obtenerPorNombre(nombreTienda).stream().findFirst().orElse(null);
    }

    @Override
    @Transactional
    public boolean comprarProducto(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = aplicacionUsuarios.buscarPorNombre(userDetails.getUsername());
        Tienda tienda = persistencia.obtener(id);

        if (tienda.getCosteV() > usuario.getMonedaV()) {
            return false;
        }

        usuario.setMonedaV(usuario.getMonedaV() - tienda.getCosteV());

        // Create a new purchase record each time a product is bought
        Compra nuevaCompra = new Compra(new CompraId(usuario, tienda));
        usuario.getCompras().add(nuevaCompra);
        tienda.getCompras().add(nuevaCompra);

        aplicacionUsuarios.insertUsuario(usuario);
        persistencia.guardar(tienda);

        // Print success message after successful transaction
        System.out.println("Despues de la compra, producto" + tienda.getCosteV() + " Usuario moneditas: " + usuario.getMonedaV());
        return true;
    }

    @Override
    @Transactional
    public List<Compra> getHistorialCompras(String username) {
        Usuario usuario = aplicacionUsuarios.buscarPorNombre(username);
        // Fetch compras eagerly using a query
        usuario = aplicacionUsuarios.buscarPorNombreConCompras(username);
        return usuario.getCompras();
    }

}
