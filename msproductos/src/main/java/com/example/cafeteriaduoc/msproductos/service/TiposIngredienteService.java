package com.example.cafeteriaduoc.msproductos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.msproductos.model.TiposIngredientes;
import com.example.cafeteriaduoc.msproductos.repository.TiposIngredientesRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class TiposIngredienteService {
    @Autowired
    private TiposIngredientesRepository tiposIngredientesRepository;

    public List<TiposIngredientes> obtenerTodos() {
        log.info("Obteniendo todas las relaciones tipos-ingrediente");
        return tiposIngredientesRepository.findAll();
    }
}