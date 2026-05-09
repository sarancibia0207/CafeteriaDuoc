package com.example.cafeteriaduoc.DTO;

import lombok.Data;

@Data
public class ProductosDTO {
    private Integer productoId;
    private String nombre;
    private double precio;
    private String tamano;
    // private Integer stock; Puede que no sea necesario mostrar el stock
}