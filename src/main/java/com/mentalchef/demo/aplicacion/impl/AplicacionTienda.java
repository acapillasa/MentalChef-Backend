package com.mentalchef.demo.aplicacion.impl;

import java.util.List;

import com.mentalchef.demo.aplicacion.IAplicacionTienda;
import com.mentalchef.demo.modelos.Respuesta;
import com.mentalchef.demo.modelos.Tienda;
import com.mentalchef.demo.persistencia.IPersistencia;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tienda")
public class AplicacionTienda implements IAplicacionTienda {

    IPersistencia<Tienda> persistencia;

    @Override
    @GetMapping("/{id}")
    public Tienda getProducto(@PathVariable Long id) {
        return persistencia.obtener(id);
    }

    @Override
    @GetMapping
    public List<Tienda> getProductos() {
        return persistencia.obtenerTodos();
    }

    @Override
    @PostMapping
    public String insertProducto(@RequestBody Tienda producto) {
        persistencia.guardar(producto);
        return "Producto insertado con éxito";
    }

    @Override
    @GetMapping("/buscar")
    public List<Tienda> getListProdcutoByName(@RequestParam String nombreTienda) {
        return persistencia.obtenerPorNombre(nombreTienda);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteProductoById(@PathVariable Long id) {
        persistencia.eliminar(persistencia.obtener(id));
    }

    @Override
    @GetMapping("/buscarUno")
    public Tienda getProdcutoByName(@RequestParam String nombreTienda) {
        return persistencia.obtenerPorNombre(nombreTienda).stream().findFirst().orElse(null);
    }
    
}
