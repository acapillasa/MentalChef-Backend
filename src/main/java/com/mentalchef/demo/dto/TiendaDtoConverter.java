package com.mentalchef.demo.dto;

import org.springframework.stereotype.Component;

import com.mentalchef.demo.modelos.Tienda;

@Component
public class TiendaDtoConverter {
    public static TiendaDto convertToTiendaDto(Tienda tienda) {

        TiendaDto tiendaDto = new TiendaDto();
        tiendaDto.setId(tienda.getId());
        tiendaDto.setNombre(tienda.getNombre());
        tiendaDto.setDescripcion(tienda.getDescripcion());
        tiendaDto.setCoste(tienda.getCoste());
        tiendaDto.setCosteV(tienda.getCosteV());
        tiendaDto.setImagen(tienda.getImagen());

        return tiendaDto;
    }

    public static Tienda convertToTienda(TiendaDto tiendaDto) {

        Tienda tienda = new Tienda();
        tienda.setNombre(tiendaDto.getNombre());
        tienda.setDescripcion(tiendaDto.getDescripcion());
        tienda.setCoste(tiendaDto.getCoste());
        tienda.setCosteV(tiendaDto.getCosteV());
        tienda.setImagen(tiendaDto.getImagen());

        return tienda;
    }
}
