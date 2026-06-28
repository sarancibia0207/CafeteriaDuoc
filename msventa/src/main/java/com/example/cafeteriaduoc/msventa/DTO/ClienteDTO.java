package com.example.cafeteriaduoc.msventa.DTO;

import lombok.Data;
    
@Data
public class ClienteDTO {
    private Integer clienteId;
    private String nombreCliente;
    private double puntosAcumulables;
}
