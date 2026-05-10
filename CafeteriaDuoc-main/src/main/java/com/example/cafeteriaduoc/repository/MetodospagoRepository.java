package com.example.cafeteriaduoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cafeteriaduoc.model.Metodospago;

import org.springframework.stereotype.Repository;

@Repository
public interface MetodospagoRepository extends JpaRepository<Metodospago, Integer> {

}
