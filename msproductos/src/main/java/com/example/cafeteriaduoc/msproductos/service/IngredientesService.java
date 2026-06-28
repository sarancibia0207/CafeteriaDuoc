package com.example.cafeteriaduoc.msproductos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.msproductos.model.Ingredientes;
import com.example.cafeteriaduoc.msproductos.repository.IngredientesRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class IngredientesService {
    @Autowired
    private IngredientesRepository ingredientesRepository;

    public List<Ingredientes> obtenerTodos(){
        log.info("Obteniendo todas las relaciones ingredientes-producto");
        return ingredientesRepository.findAll();
    }
}
