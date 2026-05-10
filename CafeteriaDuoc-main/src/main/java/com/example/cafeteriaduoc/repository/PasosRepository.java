package com.example.cafeteriaduoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cafeteriaduoc.model.Pasos;

import org.springframework.stereotype.Repository;

@Repository
public interface PasosRepository extends JpaRepository<Pasos, Integer> {

}
