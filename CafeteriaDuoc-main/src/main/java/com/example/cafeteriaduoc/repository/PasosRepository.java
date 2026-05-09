package com.example.cafeteriaduoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cafeteriaduoc.model.PasoReceta;
import org.springframework.stereotype.Repository;

@Repository
public interface PasosRepository extends JpaRepository<PasoReceta, Integer> {

}
