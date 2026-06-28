package com.example.cafeteriaduoc.msventa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cafeteriaduoc.msventa.model.Ventas;

public interface VentasRepository extends JpaRepository<Ventas, Integer> {

}
