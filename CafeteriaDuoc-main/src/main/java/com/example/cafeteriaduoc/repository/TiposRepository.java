package com.example.cafeteriaduoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cafeteriaduoc.model.Tipos;

import org.springframework.stereotype.Repository;

@Repository
public interface TiposRepository extends JpaRepository<Tipos, Integer> {

}
