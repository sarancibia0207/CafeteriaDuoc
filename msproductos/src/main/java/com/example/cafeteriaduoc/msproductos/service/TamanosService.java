package com.example.cafeteriaduoc.msproductos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.msproductos.model.Tamanos;
import com.example.cafeteriaduoc.msproductos.repository.TamanosRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class TamanosService {
    @Autowired
    private TamanosRepository tamanosRepository;

    public List<Tamanos> obtenerTodos(){
        log.info("Obteniendo todas las relaciones tamaños-producto");
        return tamanosRepository.findAll();
    }
}
