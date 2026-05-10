package com.example.cafeteriaduoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.model.Ingrediente;
import com.example.cafeteriaduoc.repository.IngredienteRepository;

@Service
@Transactional
public class IngredienteService {
    @Autowired
    private IngredienteRepository ingredienteRepository;

    public List<Ingrediente> obtenerTodas() {
        return ingredienteRepository.findAll();
    }

    public Ingrediente buscarPorId(Integer id){
        Ingrediente ingrediente = ingredienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Ingrediente no encontrado."));
        return ingrediente;
    } // Esto no sé si va

    public Ingrediente guardar(Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    public String eliminar(Integer id){
        try {
            Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imposible eliminar, el ingrediente con ID " + id + " no existe."));
            ingredienteRepository.delete(ingrediente);
            return "El ingrediente " + ingrediente.getNombreIngrediente() + " ha sido eliminado.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Ingrediente actualizarIngrediente(Integer id, Ingrediente ingrediente){
        Ingrediente ingrediente2 = ingredienteRepository.findById(id).orElseThrow(() -> new RuntimeException("El ingrediente no existe."));
        if(ingrediente.getNombreIngrediente() != null){
            ingrediente2.setNombreIngrediente(ingrediente.getNombreIngrediente());;
        }
        if(ingrediente.getCantidadIngrediente() != null){
            ingrediente2.setCantidadIngrediente(ingrediente.getCantidadIngrediente());;
        }
        return ingredienteRepository.save(ingrediente2);
    }
    
}
