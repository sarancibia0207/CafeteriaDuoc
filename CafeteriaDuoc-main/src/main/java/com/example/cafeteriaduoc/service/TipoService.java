package com.example.cafeteriaduoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.model.Tipo;
import com.example.cafeteriaduoc.repository.TipoRepository;

@Service
@Transactional
public class TipoService {
    @Autowired
    private TipoRepository tipoRepository;

    public List<Tipo> obtenerTodos(){
        return tipoRepository.findAll();
    }

    public Tipo buscarPorId(Integer id){
        Tipo tipo = tipoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tipo de producto no encontrado."));
        return tipo;
    } // Esto no sé si va

    public Tipo guardar(Tipo tipo){
        return tipoRepository.save(tipo);
    }

    public String eliminar(Integer id){
        try {
            Tipo tipo = tipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imposible eliminar, el tipo de producto con ID " + id + " no existe."));
            tipoRepository.delete(tipo);
            return "El tipo de producto " + tipo.getNombreTipo() + " ha sido eliminado.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    // Actualizar? 
}
