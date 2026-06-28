package com.example.cafeteriaduoc.msproductos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.msproductos.DTO.IngredienteDTO;
import com.example.cafeteriaduoc.msproductos.model.Ingrediente;
import com.example.cafeteriaduoc.msproductos.repository.IngredienteRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class IngredienteService {
    @Autowired
    private IngredienteRepository ingredienteRepository;

    public List<IngredienteDTO> obtenerTodas() {
        log.info("Obteniendo todos los ingredientes");
        return ingredienteRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public IngredienteDTO buscarPorId(Integer id){
        log.info("Buscando ingrediente con ID: {}", id);
        Ingrediente ingrediente = ingredienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Ingrediente no encontrado."));
        return convertirADTO(ingrediente);
    }

    public IngredienteDTO guardar(Ingrediente ingrediente) {
        log.info("Guardando nuevo ingrediente: {}", ingrediente.getNombreIngrediente());
        Ingrediente guardado = ingredienteRepository.save(ingrediente);
        return convertirADTO(guardado);
    }

    public String eliminar(Integer id){
        log.info("Eliminando ingrediente con ID: {}", id);
        try {
            Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imposible eliminar, el ingrediente con ID " + id + " no existe."));
            ingredienteRepository.delete(ingrediente);
            return "El ingrediente " + ingrediente.getNombreIngrediente() + " ha sido eliminado.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public IngredienteDTO actualizarIngrediente(Integer id, Ingrediente ingredienteActualizado){
        log.info("Actualizando ingrediente con ID: {}", id);
        Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El ingrediente con ID " + id + " no existe."));
        ingrediente.setNombreIngrediente(ingredienteActualizado.getNombreIngrediente());
        ingrediente.setCantidadIngrediente(ingredienteActualizado.getCantidadIngrediente());
        return convertirADTO(ingredienteRepository.save(ingrediente));
    }

    public IngredienteDTO patchIngrediente(Integer id, Ingrediente ingredienteActualizado) {
        log.info("Actualizando parcialmente ingrediente con ID: {}", id);
        Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El ingrediente con ID " + id + " no existe."));
        if (ingredienteActualizado.getNombreIngrediente() != null) ingrediente.setNombreIngrediente(ingredienteActualizado.getNombreIngrediente());
        if (ingredienteActualizado.getCantidadIngrediente() != null) ingrediente.setCantidadIngrediente(ingredienteActualizado.getCantidadIngrediente());
        return convertirADTO(ingredienteRepository.save(ingrediente));
    }

    private IngredienteDTO convertirADTO(Ingrediente ingrediente){
        IngredienteDTO dto = new IngredienteDTO();
        dto.setIngredienteId(ingrediente.getIngredienteId());
        dto.setNombreIngrediente(ingrediente.getNombreIngrediente());
        dto.setCantidadIngrediente(ingrediente.getCantidadIngrediente());
        return dto;
    }
    
}
