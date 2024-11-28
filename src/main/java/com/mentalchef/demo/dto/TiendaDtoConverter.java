package com.mentalchef.demo.dto;

import org.springframework.stereotype.Component;

import com.mentalchef.demo.modelos.Tienda;

@Component
public class TiendaDtoConverter {
    public static TiendaDto convertToTiendaDto(TiendaDto tiendaDto) {

        TiendaDto TiendaDto = new TiendaDto();
        TiendaDto.setId(tiendaDto.getId());
        TiendaDto.setNombre(tiendaDto.getNombre());
        TiendaDto.setDescripcion(tiendaDto.getDescripcion());
        TiendaDto.setCoste(tiendaDto.getCoste());

        return tiendaDto;
    }

    public static Tienda convertToTienda(TiendaDto tiendaDto) {

        Tienda tienda = new Tienda();
        tienda.setNombre(tiendaDto.getNombre());
        tienda.setDescripcion(tiendaDto.getDescripcion());
        tienda.setCoste(tiendaDto.getCoste());

        return tienda;
    }
}
