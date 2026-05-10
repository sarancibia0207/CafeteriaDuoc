package com.example.cafeteriaduoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.model.ProductoVenta;
import com.example.cafeteriaduoc.repository.ProductoventaRepository;

@Service
@Transactional
public class ProductoVentaService {
    @Autowired
    private ProductoventaRepository productoventaRepository;

    // Añadir? No sé si va esto. Comentar en cualquier caso.
    public String anadirProductoVenta(ProductoVenta productoVenta){
        productoventaRepository.save(productoVenta);
        return "El producto " + productoVenta.getProducto().getNombre() + " ha sido asignado la venta " + productoVenta.getVenta().getVentaId();
    }

    public List<ProductoVenta> obtenerTodos(){
        return productoventaRepository.findAll();
    }
}
