package com.example.cafeteriaduoc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cafeteriaduoc.DTO.ProductosDTO;
import com.example.cafeteriaduoc.model.Ingredientes;
import com.example.cafeteriaduoc.model.Pasos;
import com.example.cafeteriaduoc.model.Productos;
import com.example.cafeteriaduoc.model.Tamanos;
import com.example.cafeteriaduoc.model.Tipos;
import com.example.cafeteriaduoc.repository.ProductosRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductosService {
    @Autowired
    private ProductosRepository productosRepository;

    public List<ProductosDTO> obtenerTodos(){
        return productosRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();      
    }

    public ProductosDTO buscarPorId(Integer productoId){
        Productos productos = productosRepository.findById(productoId)
            .orElseThrow(() -> new RuntimeException("¡Producto no encontrado!"));
        return convertirADTO(productos);
    }

    public Productos guardarProductos(Productos productos){
        return productosRepository.save(productos);
    }

    public Productos actualizarProductos(Integer productoId, Productos producto){
        Productos producto2 = productosRepository.findById(productoId).orElseThrow(() -> new RuntimeException("¡El producto no existe en los registros!"));
        if(producto.getStock() != null){
            producto2.setStock(producto.getStock());
        }
        if(producto.getNombre() != null){
            producto2.setNombre(producto.getNombre());
        }
        if(producto.getPrecio() != null){
            producto2.setPrecio(producto.getPrecio());
        }
        return productosRepository.save(producto2);
    }

    public List<ProductosDTO> buscarPorNombre(String nombre){
        return productosRepository.findByNombre(nombre).stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    public List<ProductosDTO> buscarStock(Integer stockMinimo){
        return productosRepository.findByStock(stockMinimo).stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    private ProductosDTO convertirADTO(Productos productos){
        ProductosDTO dto = new ProductosDTO();
        dto.setProductoId(productos.getProductoId());
        dto.setNombre(productos.getNombre());
        dto.setPrecio(productos.getPrecio());
        List<String> tamanos = new ArrayList<>();
        if(productos.getTamanosProductos() != null) {
            for(Tamanos tamano : productos.getTamanosProductos()) {
                tamanos.add(tamano.getTamano().getNombreTamano());
            }
        }
        dto.setTamanos(tamanos); // Al parecer esto devolverá todos los tamaños disponibles. No sé si está bien xd
        List<String> ingredientes = new ArrayList<>();
        if(productos.getIngredientesProductos() != null){
            for (Ingredientes ingrediente : productos.getIngredientesProductos()) {
                ingredientes.add(ingrediente.getIngrediente().getNombreIngrediente());
            }
        }
        dto.setIngredientes(ingredientes); // Creo que esto devolverá todos los ingredientes disponibles. No sé si está bien xd
        List<String> tipos = new ArrayList<>();
        if(productos.getTiposProductos() != null){
            for (Tipos tipo : productos.getTiposProductos()){
                tipos.add(tipo.getTipo().getNombreTipo());
            }
        }
        dto.setTipos(tipos); // ^
        List<String> recetas = new ArrayList<>();
        if(productos.getPasos() != null){
            for (Pasos paso: productos.getPasos()) {
                recetas.add(paso.getPasoReceta().getTituloReceta());
            }
        }
        dto.setRecetas(recetas); // ^
        return dto;
    }
}