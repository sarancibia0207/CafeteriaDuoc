package com.example.cafeteriaduoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.model.Tipos;
import com.example.cafeteriaduoc.repository.TiposRepository;

@Service
@Transactional
public class TiposService {
    @Autowired
    private TiposRepository tiposRepository;

    // Añadir? No sé si va esto. Comentar en cualquier caso.
    public String anadirTipo(Tipos tipo){
        tiposRepository.save(tipo);
        return "El producto " + tipo.getProducto().getNombre() + " ha sido asignado el tipo " + tipo.getTipo().getNombreTipo();
    }

    public List<Tipos> obtenerTodos() {
        return tiposRepository.findAll();
    }
}