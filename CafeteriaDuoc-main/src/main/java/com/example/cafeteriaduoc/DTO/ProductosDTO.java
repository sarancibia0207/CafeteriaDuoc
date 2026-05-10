package com.example.cafeteriaduoc.DTO;

import java.util.List;

import lombok.Data;

@Data
public class ProductosDTO {
    private Integer productoId;
    private String nombre;
    private Double precio;
    private List<String> tamanos;
    private List<String> ingredientes;
    private List<String> tipos;
    private List<String> recetas;
    // private Integer stock; Puede que no sea necesario mostrar el stock
}