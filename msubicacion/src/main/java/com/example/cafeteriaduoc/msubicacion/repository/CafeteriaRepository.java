package com.example.cafeteriaduoc.msubicacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cafeteriaduoc.msubicacion.model.Cafeteria;

public interface CafeteriaRepository extends JpaRepository<Cafeteria, Integer> {
    
}
