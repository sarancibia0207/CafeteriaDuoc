package com.example.cafeteriaduoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.cafeteriaduoc.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query("SELECT c FROM Cliente c WHERE c.clienteRut = :rut")
    Cliente findByClienteRut(String rut);

    @Query("SELECT c FROM Cliente c WHERE c.nombreCliente = :nombre")
    Cliente findByNombreCliente(String nombre);

    
}
