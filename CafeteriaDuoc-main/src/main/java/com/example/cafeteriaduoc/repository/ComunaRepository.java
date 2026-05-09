package com.example.cafeteriaduoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cafeteriaduoc.model.Comuna;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer> {

}
