package com.example.cafeteriaduoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cafeteriaduoc.DTO.ProductosDTO;
import com.example.cafeteriaduoc.Repository.ProductosRepository;
import com.example.cafeteriaduoc.model.Productos;

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

    public Productos actualizarProductos(Integer productoId, Productos productos){
        Productos producto = productosRepository.findById(productoId).orElseThrow(() -> new RuntimeException("¡El producto no existe en los registros!"));
        if(productos.getStock() != null){
            producto.setStock(productos.getStock());
        }
        if(productos.getNombre() != null){
            producto.setNombre(productos.getNombre());
        }
        return productosRepository.save(producto);
    }

    public List<ProductosDTO> buscarPorNombre(String nombre){
        return productosRepository.findByNombre(nombre).stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    public List<ProductosDTO> buscarStock(Integer stockMinimo){
        return productosRepository.buscarStock(stockMinimo).stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    private ProductosDTO conventiRADTO(Productos productos){
        ProductosDTO dto = new ProductosDTO();
        dto.setProductoId(productos.getProductoId());
        dto.setNombre(productos.getNombre());
        dto.setPrecio(productos.getPrecio());
        dto.setTamano(productos.getTamanosProductos());
    }
}
