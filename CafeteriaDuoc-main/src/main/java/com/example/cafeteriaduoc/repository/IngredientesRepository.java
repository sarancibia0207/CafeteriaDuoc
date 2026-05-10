package com.example.cafeteriaduoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cafeteriaduoc.model.Ingredientes;

import org.springframework.stereotype.Repository;

@Repository
public interface IngredientesRepository extends JpaRepository<Ingredientes, Integer> {

}
