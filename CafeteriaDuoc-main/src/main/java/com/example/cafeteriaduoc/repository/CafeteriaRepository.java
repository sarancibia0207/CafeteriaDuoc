package com.example.cafeteriaduoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cafeteriaduoc.model.Cafeteria;

@Repository
public interface CafeteriaRepository extends JpaRepository<Cafeteria, Integer> {

}
