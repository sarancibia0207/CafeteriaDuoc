package com.example.cafeteriaduoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.model.TipoIngrediente;
import com.example.cafeteriaduoc.repository.TipoIngredienteRepository;

@Service
@Transactional
public class TipoIngredienteService {
    @Autowired
    private TipoIngredienteRepository tipoIngredienteRepository;

    public List<TipoIngrediente> obtenerTodos(){
        return tipoIngredienteRepository.findAll();
    }

    public TipoIngrediente buscarPorId(Integer id){
        TipoIngrediente tipoIngrediente = tipoIngredienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Tipo de ingrediente no encontrado."));
        return tipoIngrediente;
    } // Esto no sé si va

    public TipoIngrediente guardar(TipoIngrediente tipoIngrediente){
        return tipoIngredienteRepository.save(tipoIngrediente);
    }

    public String eliminar(Integer id){
        try {
            TipoIngrediente tipoIngrediente = tipoIngredienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imposible eliminar, el tipo de ingrediente con ID " + id + " no existe."));
            tipoIngredienteRepository.delete(tipoIngrediente);
            return "El tipo de ingrediente " + tipoIngrediente.getNombreTipoIngrediente() + " ha sido eliminado.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    // Actualizar? 
}
