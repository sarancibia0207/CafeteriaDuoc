package com.example.cafeteriaduoc.DTO;

import java.util.List;

import java.sql.Date;

import lombok.Data;

@Data
public class VentasDTO {
    private Integer ventaId;
    private String nombreCafeteria;
    private Date fechaVenta;
    private String nombreCliente;
    private List<String> productos;
    private List<String> metodoPago;
    private Double totalVenta;
}