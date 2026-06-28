package com.example.cafeteriaduoc.msproductos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.msproductos.DTO.TipoIngredienteDTO;
import com.example.cafeteriaduoc.msproductos.model.TipoIngrediente;
import com.example.cafeteriaduoc.msproductos.repository.TipoIngredienteRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class TipoIngredienteService {
    @Autowired
    private TipoIngredienteRepository tipoIngredienteRepository;

    public List<TipoIngredienteDTO> obtenerTodos(){
        log.info("Obteniendo todos los tipos de ingrediente");
        return tipoIngredienteRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public TipoIngredienteDTO buscarPorId(Integer id){
        log.info("Buscando tipo de ingrediente con ID: {}", id);
        TipoIngrediente tipoIngrediente = tipoIngredienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Tipo de ingrediente no encontrado."));
        return convertirADTO(tipoIngrediente);
    } 

    public TipoIngredienteDTO guardar(TipoIngrediente tipoIngrediente){
        log.info("Guardando nuevo tipo de ingrediente: {}", tipoIngrediente.getNombreTipoIngrediente());
        TipoIngrediente guardado = tipoIngredienteRepository.save(tipoIngrediente);
        return convertirADTO(guardado);
    }

    public String eliminar(Integer id){
        log.info("Eliminando tipo de ingrediente con ID: {}", id);
        try {
            TipoIngrediente tipoIngrediente = tipoIngredienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imposible eliminar, el tipo de ingrediente con ID " + id + " no existe."));
            tipoIngredienteRepository.delete(tipoIngrediente);
            return "El tipo de ingrediente " + tipoIngrediente.getNombreTipoIngrediente() + " ha sido eliminado.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    private TipoIngredienteDTO convertirADTO(TipoIngrediente tipoIngrediente){
        TipoIngredienteDTO dto = new TipoIngredienteDTO();
        dto.setNombreTipoIngrediente(tipoIngrediente.getNombreTipoIngrediente());
        dto.setTipoIngredienteId(tipoIngrediente.getTipoIngredienteId());
        return dto;
    }
}
