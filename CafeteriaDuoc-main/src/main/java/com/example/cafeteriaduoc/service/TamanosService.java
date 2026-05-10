package com.example.cafeteriaduoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.model.Tamanos;
import com.example.cafeteriaduoc.repository.TamanosRepository;

@Service
@Transactional
public class TamanosService {
    @Autowired
    private TamanosRepository tamanosRepository;

    // Añadir? No sé si va esto. Comentar en cualquier caso.
    public String anadirTamanos(Tamanos tamanos){
        tamanosRepository.save(tamanos);
        return "El producto " + tamanos.getTamano().getNombreTamano() + " ha sido asignado el tamano " + tamanos.getTamano().getNombreTamano();
    }

    public List<Tamanos> obtenerTodos(){
        return tamanosRepository.findAll();
    }
}
