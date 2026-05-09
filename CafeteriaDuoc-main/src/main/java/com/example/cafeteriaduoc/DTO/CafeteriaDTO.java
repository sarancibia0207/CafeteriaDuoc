package com.example.cafeteriaduoc.DTO;

import lombok.Data;

@Data
public class CafeteriaDTO {
    private Integer cafeteriaId;
    private String nombreCafeteria;
    private String nombreComuna;
    // private Integer cantidadVentas; Creo que podría ser un atributo, pero no sé si es posible.
}