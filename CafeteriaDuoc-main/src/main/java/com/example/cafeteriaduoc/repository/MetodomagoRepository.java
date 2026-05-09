package com.example.cafeteriaduoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cafeteriaduoc.model.Metodopago;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodomagoRepository extends JpaRepository<Metodopago, Integer> {

}
