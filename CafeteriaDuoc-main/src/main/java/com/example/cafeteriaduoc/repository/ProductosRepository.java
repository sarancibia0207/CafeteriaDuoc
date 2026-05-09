package com.example.cafeteriaduoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.cafeteriaduoc.model.Productos;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Integer> {
    List<Productos> findByProductoId(Integer productoId); //son redundantes (ya vienen base en el JPA)

    List<Productos> findByProductoNombre(String nombre); //son redundantes (ya vienen base en el JPA)

    @Query("SELECT p FROM Productos p WHERE p.productoStock >= :stock")
    List<Productos> findByStock(@Param("stock") Integer stock);
    
}
