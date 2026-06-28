package com.example.cafeteriaduoc.msventa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cafeteriaduoc.msventa.model.ProductoVenta;
import com.example.cafeteriaduoc.msventa.service.ProductoVentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/productoventa")
@Tag(name = "Producto Venta", description = "Asociación de productos a ventas")
public class ProductoVentaController {

    @Autowired
    private ProductoVentaService productoVentaService;

    @PostMapping
    @Operation(summary = "Agregar producto a una venta", description = "Asocia un producto a una venta existente")
    public ResponseEntity<?> agregarProductoVenta(@RequestBody ProductoVenta productoVenta) {
        try {
            ProductoVenta guardado = productoVentaService.guardar(productoVenta);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}