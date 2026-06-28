package com.example.cafeteriaduoc.msproductos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.msproductos.DTO.PasoRecetaDTO;
import com.example.cafeteriaduoc.msproductos.model.PasoReceta;
import com.example.cafeteriaduoc.msproductos.repository.PasorecetaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class PasoRecetaService {
    @Autowired
    private PasorecetaRepository pasorecetaRepository;

    public List<PasoRecetaDTO> obtenerTodos(){
        log.info("Obteniendo todas las recetas");
        return pasorecetaRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public PasoRecetaDTO buscarPorId(Integer id){
        log.info("Buscando receta con ID: {}", id);
        PasoReceta pasoReceta = pasorecetaRepository.findById(id).orElseThrow(() -> new RuntimeException("Receta no encontrada."));
        return convertirADTO(pasoReceta);
    }

    public PasoRecetaDTO guardar(PasoReceta pasoReceta){
        log.info("Guardando nueva receta: {}", pasoReceta.getTituloReceta());
        PasoReceta guardado =  pasorecetaRepository.save(pasoReceta);
        return convertirADTO(guardado);
    }

    public String eliminar(Integer id){
        log.info("Eliminando receta con ID: {}", id);
        try {
            PasoReceta pasoReceta = pasorecetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imposible eliminar, la receta con ID " + id + " no existe."));
            pasorecetaRepository.delete(pasoReceta);
            return "La receta " + pasoReceta.getTituloReceta() + " ha sido eliminada.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public PasoRecetaDTO actualizarPasoReceta(Integer id, PasoReceta pasoRecetaActualizado) {
        log.info("Actualizando receta con ID: {}", id);
        PasoReceta pasoReceta = pasorecetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La receta con ID " + id + " no existe."));
        pasoReceta.setTituloReceta(pasoRecetaActualizado.getTituloReceta());
        pasoReceta.setDescripcionPaso(pasoRecetaActualizado.getDescripcionPaso());
        return convertirADTO(pasorecetaRepository.save(pasoReceta));
    }

    public PasoRecetaDTO patchPasoReceta(Integer id, PasoReceta pasoRecetaActualizado) {
        log.info("Actualizando parcialmente receta con ID: {}", id);
        PasoReceta pasoReceta = pasorecetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La receta con ID " + id + " no existe."));
        if (pasoRecetaActualizado.getTituloReceta() != null) pasoReceta.setTituloReceta(pasoRecetaActualizado.getTituloReceta());
        if (pasoRecetaActualizado.getDescripcionPaso() != null) pasoReceta.setDescripcionPaso(pasoRecetaActualizado.getDescripcionPaso());
        return convertirADTO(pasorecetaRepository.save(pasoReceta));
    }

    private PasoRecetaDTO convertirADTO(PasoReceta pasoReceta){
        PasoRecetaDTO dto = new PasoRecetaDTO();
        dto.setPasoRecetaId(pasoReceta.getPasoRecetaId());
        dto.setTituloReceta(pasoReceta.getTituloReceta());
        dto.setDescripcionPaso(pasoReceta.getDescripcionPaso());
        return dto;
    }
}
