package com.example.cafeteriaduoc.DTO;

import lombok.Data;

@Data
public class ClienteDTO {
    private Integer clienteId;
    private String nombreCliente;
    // private String rutCliente; Creo que por la protección de datos personales esto no debería mostrarse
    // private Date fechaNacimiento; Same shit ^^
    private double puntosAcumulables;
}
