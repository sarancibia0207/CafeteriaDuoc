package com.example.cafeteriaduoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cafeteriaduoc.model.Tipo;
import org.springframework.stereotype.Repository;

@Repository
public interface TiposRepository extends JpaRepository<Tipo, Integer> {

}
