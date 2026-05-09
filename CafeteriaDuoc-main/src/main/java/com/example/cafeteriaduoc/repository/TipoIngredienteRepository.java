package com.example.cafeteriaduoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cafeteriaduoc.model.TipoIngrediente;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoIngredienteRepository extends JpaRepository<TipoIngrediente, Integer> {

}
