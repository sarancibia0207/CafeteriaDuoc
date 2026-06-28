package com.example.cafeteriaduoc.mscliente.DTO;

import lombok.Data;

@Data
public class ClienteDTO {
    private Integer clienteId;
    private String nombreCliente;
    private double puntosAcumulables;
}
