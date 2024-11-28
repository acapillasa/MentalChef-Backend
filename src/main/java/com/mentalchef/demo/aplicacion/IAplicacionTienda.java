package com.mentalchef.demo.aplicacion;

import java.util.List;

import com.mentalchef.demo.modelos.Tienda;

public interface IAplicacionTienda {

    public Tienda getProducto(Long id);

    public List<Tienda> getProductos();

    public String insertProducto(Tienda producto);

    public List<Tienda> getListProdcutoByName(String Tienda);

    public void deleteProductoById(Long id);

    public Tienda getProdcutoByName(String Tienda);
}
