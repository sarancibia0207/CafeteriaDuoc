package com.example.cafeteriaduoc.msproductos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.msproductos.model.Pasos;
import com.example.cafeteriaduoc.msproductos.repository.PasosRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class PasosService {
    @Autowired
    private PasosRepository pasosRepository;

    public List<Pasos> obtenerTodos(){
        log.info("Obteniendo todos los pasos de recetas");
        return pasosRepository.findAll();
    }
}
