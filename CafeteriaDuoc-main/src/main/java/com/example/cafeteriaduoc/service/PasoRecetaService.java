package com.example.cafeteriaduoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.model.PasoReceta;
import com.example.cafeteriaduoc.repository.PasorecetaRepository;

@Service
@Transactional
public class PasoRecetaService {
    @Autowired
    private PasorecetaRepository pasorecetaRepository;

    public List<PasoReceta> obtenerTodos(){
        return pasorecetaRepository.findAll();
    }

    public PasoReceta buscarPorId(Integer id){
        PasoReceta pasoReceta = pasorecetaRepository.findById(id).orElseThrow(() -> new RuntimeException("Receta no encontrada."));
        return pasoReceta;
    } // Esto no sé si va

    public PasoReceta guardar(PasoReceta pasoReceta){
        return pasorecetaRepository.save(pasoReceta);
    }

    public String eliminar(Integer id){
        try {
            PasoReceta pasoReceta = pasorecetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imposible eliminar, la receta con ID " + id + " no existe."));
            pasorecetaRepository.delete(pasoReceta);
            return "La receta " + pasoReceta.getTituloReceta() + " ha sido eliminada.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public PasoReceta actualizar(Integer id, PasoReceta pasoReceta){
        PasoReceta pasoReceta2 = pasorecetaRepository.findById(id).orElseThrow(() -> new RuntimeException("La receta no existe."));
        if(pasoReceta.getTituloReceta() != null){
            pasoReceta2.setTituloReceta(pasoReceta.getTituloReceta());
        }
        if(pasoReceta.getDescripcionPaso() != null){
            pasoReceta2.setDescripcionPaso(pasoReceta.getDescripcionPaso());
        }
        return pasorecetaRepository.save(pasoReceta2);
    }
}
