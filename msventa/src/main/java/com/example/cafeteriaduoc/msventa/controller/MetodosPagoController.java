package com.example.cafeteriaduoc.msventa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cafeteriaduoc.msventa.model.Metodospago;
import com.example.cafeteriaduoc.msventa.service.MetodospagoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/ventasmetodospago")
@Tag(name = "Ventas Métodos de Pago", description = "Asociación de métodos de pago a ventas")
public class MetodosPagoController {

    @Autowired
    private MetodospagoService metodospagoService;

    @PostMapping
    @Operation(summary = "Agregar método de pago a una venta", description = "Asocia un método de pago a una venta existente")
    public ResponseEntity<?> agregarMetodoPago(@RequestBody Metodospago metodospago) {
        try {
            Metodospago guardado = metodospagoService.guardar(metodospago);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}