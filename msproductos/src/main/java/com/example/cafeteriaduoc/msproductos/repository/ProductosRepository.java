package com.example.cafeteriaduoc.msproductos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.cafeteriaduoc.msproductos.model.Producto;

public interface ProductosRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByNombre(String nombre); //son redundantes (ya vienen base en el JPA)

    @Query("SELECT p FROM Producto p WHERE p.stock >= :stock")
    List<Producto> findByStock(@Param("stock") Integer stock);
    
    @Query("SELECT p FROM Producto p WHERE p.precio >= :precio")
    List<Producto> findByPrecio(@Param("precio") Integer precio);

    
}
