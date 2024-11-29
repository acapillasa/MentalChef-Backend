package com.mentalchef.demo.aplicacion.impl;

import java.util.List;

import com.mentalchef.demo.aplicacion.IAplicacionTienda;
import com.mentalchef.demo.aplicacion.IAplicacionUsuarios;
import com.mentalchef.demo.modelos.Compra;
import com.mentalchef.demo.modelos.CompraId;
import com.mentalchef.demo.modelos.Respuesta;
import com.mentalchef.demo.modelos.Tienda;
import com.mentalchef.demo.modelos.Usuario;
import com.mentalchef.demo.persistencia.IPersistencia;

import lombok.AllArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

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


        System.out.println("Tienda "+ tienda.getCosteV() +" Usuario: " + usuario.getMonedaV());
        if (tienda.getCosteV() < usuario.getMonedaV()) {
            return false;
        }

        usuario.setMonedaV(usuario.getMonedaV() - tienda.getCosteV());
        Compra compra = new Compra(new CompraId(usuario, tienda), 1);
        usuario.getCompras().add(compra);
        aplicacionUsuarios.insertUsuario(usuario);
        
        // Save the Compra entity
        persistencia.guardar(tienda);
        
        System.out.println(usuario.getCompras());
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
