package com.mentalchef.demo.aplicacion;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import com.mentalchef.demo.modelos.Compra;
import com.mentalchef.demo.modelos.Tienda;

public interface IAplicacionTienda {

    public Tienda getProducto(Long id);

    public List<Tienda> getProductos();

    public String insertProducto(Tienda producto);

    public List<Tienda> getListProdcutoByName(String Tienda);

    public void deleteProductoById(Long id);

    public Tienda getProdcutoByName(String Tienda);

    public boolean comprarProducto(Long id, @AuthenticationPrincipal UserDetails userDetails);

    public List<Compra> getHistorialCompras(String username);

    public void insertCompra(Compra compra);
}
