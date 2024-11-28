package com.mentalchef.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;

import com.mentalchef.demo.aplicacion.IAplicacionTienda;
import com.mentalchef.demo.dto.TiendaDto;
import com.mentalchef.demo.dto.TiendaDtoConverter;
import com.mentalchef.demo.modelos.Tienda;

@RestController
@RequestMapping("/tienda")
public class TiendaController {

    @Autowired
    IAplicacionTienda aplicacionTienda;

    @Autowired
    TiendaDtoConverter tiendaDtoConverter;

    @GetMapping("/{id}")
    public ResponseEntity<Tienda> getProdcuto(@PathVariable Long id) {
        Tienda tienda = aplicacionTienda.getProducto(id);
        return ResponseEntity.ok(tienda);
    }

    @PostMapping
    public ResponseEntity<String> insertarProducto(@RequestBody TiendaDto tiendaDto) {
        Tienda tienda = tiendaDtoConverter.convertToTienda(tiendaDto);

        aplicacionTienda.insertProducto(tienda);

        return ResponseEntity.ok("Producto insertado con éxito");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> EliminarProducto(@PathVariable Long id) {
        aplicacionTienda.deleteProductoById(id);
        return ResponseEntity.ok("Producto eliminado con éxito");
    }
}
