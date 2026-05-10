package com.example.cafeteriaduoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.model.TiposIngredientes;
import com.example.cafeteriaduoc.repository.TiposIngredientesRepository;

@Service
@Transactional
public class TiposIngredienteService {
    @Autowired
    private TiposIngredientesRepository tiposIngredientesRepository;

    // Añadir? No sé si va esto. Comentar en cualquier caso.
    public String anadirTipoIngrediente(TiposIngredientes tipoIngrediente){
        tiposIngredientesRepository.save(tipoIngrediente);
        return "El ingrediente " + tipoIngrediente.getIngrediente().getNombreIngrediente() + " ha sido asignado el tipo " + tipoIngrediente.getTipoIngrediente().getNombreTipoIngrediente();
    }

    public List<TiposIngredientes> obtenerTodos() {
        return tiposIngredientesRepository.findAll();
    }
}