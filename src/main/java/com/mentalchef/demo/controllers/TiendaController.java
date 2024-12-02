package com.mentalchef.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mentalchef.demo.aplicacion.IAplicacionTienda;
import com.mentalchef.demo.aplicacion.IAplicacionUsuarios;
import com.mentalchef.demo.dto.CompraDto;
import com.mentalchef.demo.dto.CompraDtoConverter;
import com.mentalchef.demo.dto.TiendaDto;
import com.mentalchef.demo.dto.TiendaDtoConverter;
import com.mentalchef.demo.modelos.Compra;
import com.mentalchef.demo.modelos.Tienda;

@RestController
@RequestMapping("/tienda")
public class TiendaController {

    @Autowired
    IAplicacionTienda aplicacionTienda;

    @Autowired
    TiendaDtoConverter tiendaDtoConverter;

    IAplicacionUsuarios aplicacionUsuarios;

    CompraDtoConverter compraDtoConverter;

    @GetMapping("/productos")
    public ResponseEntity<List<TiendaDto>> getProductos() {
        List<Tienda> tiendas = aplicacionTienda.getProductos();
        List<TiendaDto> tiendasDto = new ArrayList<TiendaDto>();
        for (Tienda tienda : tiendas) {
            tiendasDto.add(tiendaDtoConverter.convertToTiendaDto(tienda));
        }
        return ResponseEntity.ok(tiendasDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TiendaDto> getProdcuto(@PathVariable Long id) {
        Tienda tienda = aplicacionTienda.getProducto(id);
        TiendaDto tiendaDto = tiendaDtoConverter.convertToTiendaDto(tienda);
        return ResponseEntity.ok(tiendaDto);
    }

    @PostMapping("/insertarProducto")
    public ResponseEntity<String> insertarProducto(@RequestBody TiendaDto tiendaDto) {
        Tienda tienda = tiendaDtoConverter.convertToTienda(tiendaDto);

        aplicacionTienda.insertProducto(tienda);

        return ResponseEntity.ok("Producto insertado con éxito");
    }

    @DeleteMapping("/eliminarProducto/{id}")
    public ResponseEntity<String> EliminarProducto(@PathVariable Long id) {
        aplicacionTienda.deleteProductoById(id);
        return ResponseEntity.ok("Producto eliminado con éxito");
    }

    @PostMapping("/comprar/{id}")
    public ResponseEntity<String> comprarProducto(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        boolean success = aplicacionTienda.comprarProducto(id, userDetails);
        if (success == true) {
            return ResponseEntity.ok("Compra realizada con éxito.");
        } else {
            return ResponseEntity.ok("Error al realizar la compra, no tienes puntos suficientes.");
        }
    }

    @GetMapping("/historialCompras")
    public ResponseEntity<List<CompraDto>> getHistorialCompras(@AuthenticationPrincipal UserDetails userDetails) {
        List<CompraDto> comprasDto = new ArrayList<CompraDto>();
        for (Compra compra : aplicacionTienda.getHistorialCompras(userDetails.getUsername())) {
            comprasDto.add(compraDtoConverter.convertToCompraDto(compra));
        }
        return ResponseEntity.ok(comprasDto);
    }

}
