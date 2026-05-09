package com.example.cafeteriaduoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cafeteriaduoc.model.ProductoVenta;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoventaRepository extends JpaRepository<ProductoVenta, Integer> {

}
