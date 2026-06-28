package com.example.cafeteriaduoc.msproductos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cafeteriaduoc.msproductos.DTO.ProductosDTO;
import com.example.cafeteriaduoc.msproductos.model.Ingredientes;
import com.example.cafeteriaduoc.msproductos.model.Pasos;
import com.example.cafeteriaduoc.msproductos.model.Producto;
import com.example.cafeteriaduoc.msproductos.model.Tamanos;
import com.example.cafeteriaduoc.msproductos.model.Tipos;
import com.example.cafeteriaduoc.msproductos.repository.ProductosRepository;

import jakarta.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ProductosService {
    @Autowired
    private ProductosRepository productosRepository;

    public List<ProductosDTO> obtenerTodos(){
        log.info("Obteniendo todos los productos");
        return productosRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();      
    }

    public ProductosDTO buscarPorId(Integer productoId){
        log.info("Buscando producto con ID: {}", productoId);
        Producto productos = productosRepository.findById(productoId)
            .orElseThrow(() -> new RuntimeException("¡Producto no encontrado!"));
        return convertirADTO(productos);
    }

    public ProductosDTO guardarProductos(Producto productos){
        log.info("Guardando nuevo producto: {}", productos.getNombre());
        Producto guardado = productosRepository.save(productos);
        return convertirADTO(guardado);
    }

    public ProductosDTO actualizarProducto(Integer id, Producto productoActualizado) {
        log.info("Actualizando producto con ID: {}", id);
        Producto producto = productosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El producto con ID " + id + " no existe."));
        producto.setNombre(productoActualizado.getNombre());
        producto.setPrecio(productoActualizado.getPrecio());
        producto.setStock(productoActualizado.getStock());
        return convertirADTO(productosRepository.save(producto));
    }

    public ProductosDTO patchProducto(Integer id, Producto productoActualizado) {
        log.info("Actualizando parcialmente producto con ID: {}", id);
        Producto producto = productosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El producto con ID " + id + " no existe."));
        if (productoActualizado.getNombre() != null) producto.setNombre(productoActualizado.getNombre());
        if (productoActualizado.getPrecio() != null) producto.setPrecio(productoActualizado.getPrecio());
        if (productoActualizado.getStock() != null) producto.setStock(productoActualizado.getStock());
        return convertirADTO(productosRepository.save(producto));
    }

    public String eliminarProducto(Integer id){
        log.info("Eliminando producto con ID: {}", id);
        try {
            Producto productos = productosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imposible eliminar, el producto con ID " + id + " no existe."));
            productosRepository.delete(productos);
            return "El producto " + productos.getNombre() + " ha sido eliminado.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public List<ProductosDTO> buscarPorNombre(String nombre){
        log.info("Buscando productos con nombre: {}", nombre);
        return productosRepository.findByNombre(nombre).stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    public List<ProductosDTO> buscarPorPrecio(Integer precio){
        log.info("Buscando productos con precio: {}", precio);
        return productosRepository.findByPrecio(precio).stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    public List<ProductosDTO> buscarStock(Integer stockMinimo){
        log.info("Buscando productos con stock mínimo: {}", stockMinimo);
        return productosRepository.findByStock(stockMinimo).stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    private ProductosDTO convertirADTO(Producto productos){
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
        dto.setTamanos(tamanos); 
        List<String> ingredientes = new ArrayList<>();
        if(productos.getIngredientesProductos() != null){
            for (Ingredientes ingrediente : productos.getIngredientesProductos()) {
                ingredientes.add(ingrediente.getIngrediente().getNombreIngrediente());
            }
        }
        dto.setIngredientes(ingredientes); 
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