package com.example.cafeteriaduoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cafeteriaduoc.model.Tamano;
import org.springframework.stereotype.Repository;

@Repository
public interface TamanosRepository extends JpaRepository<Tamano, Integer> {

}
