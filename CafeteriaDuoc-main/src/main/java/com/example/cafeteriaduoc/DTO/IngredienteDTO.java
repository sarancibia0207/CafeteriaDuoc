package com.example.cafeteriaduoc.DTO;

import java.util.List;

import lombok.Data;

@Data
public class IngredienteDTO {
    private Integer ingredienteId;
    private String nombreIngrediente;
    private double cantidadIngrediente;
    private List<String> ingredientes;
    // private List<String> tiposIngredientes; Creo que esto sería información de más ?
}
