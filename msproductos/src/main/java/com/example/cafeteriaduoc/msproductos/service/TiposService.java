package com.example.cafeteriaduoc.msproductos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.msproductos.model.Tipos;
import com.example.cafeteriaduoc.msproductos.repository.TiposRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class TiposService {
    @Autowired
    private TiposRepository tiposRepository;

    public List<Tipos> obtenerTodos() {
        log.info("Obteniendo todas las relaciones tipos-producto");
        return tiposRepository.findAll();
    }
}