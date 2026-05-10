package com.example.cafeteriaduoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.model.Pasos;
import com.example.cafeteriaduoc.repository.PasosRepository;

@Service
@Transactional
public class PasosService {
    @Autowired
    private PasosRepository pasosRepository;

    // Añadir? No sé si va esto. Comentar en cualquier caso.
    public String anadirPasos(Pasos pasos){
        pasosRepository.save(pasos);
        return "El producto " + pasos.getProducto().getNombre() + " ha sido asignado los pasos " + pasos.getPasoReceta().getDescripcionPaso();
    }

    public List<Pasos> obtenerTodos(){
        return pasosRepository.findAll();
    }
}
