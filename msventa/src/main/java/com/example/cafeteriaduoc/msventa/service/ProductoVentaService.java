package com.example.cafeteriaduoc.msventa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.msventa.model.ProductoVenta;
import com.example.cafeteriaduoc.msventa.repository.ProductoventaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ProductoVentaService {
    @Autowired
    private ProductoventaRepository productoventaRepository;

    public List<ProductoVenta> obtenerTodos(){
        log.info("Obteniendo todos los productos de venta");
        return productoventaRepository.findAll();
    }

    public ProductoVenta guardar(ProductoVenta productoVenta) {
        log.info("Guardando producto para venta");
        return productoventaRepository.save(productoVenta);
    }
}
