package com.example.cafeteriaduoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.model.Ingredientes;
import com.example.cafeteriaduoc.repository.IngredientesRepository;

@Service
@Transactional
public class IngredientesService {
    @Autowired
    private IngredientesRepository ingredientesRepository;

    // Añadir? No sé si va esto. Comentar en cualquier caso.
    public String anadirIngredientes(Ingredientes ingredientes){
        ingredientesRepository.save(ingredientes);
        return "El producto " + ingredientes.getProductos().getNombre() + " ha sido asignado los ingredientes " + ingredientes.getIngrediente().getNombreIngrediente();
    }

    public List<Ingredientes> obtenerTodos(){
        return ingredientesRepository.findAll();
    }
}
