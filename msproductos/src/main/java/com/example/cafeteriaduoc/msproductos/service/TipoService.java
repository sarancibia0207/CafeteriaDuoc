package com.example.cafeteriaduoc.msproductos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.msproductos.DTO.TipoDTO;
import com.example.cafeteriaduoc.msproductos.model.Tipo;
import com.example.cafeteriaduoc.msproductos.repository.TipoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class TipoService {
    @Autowired
    private TipoRepository tipoRepository;

    public List<TipoDTO> obtenerTodos(){
        log.info("Obteniendo todos los tipos de producto");
        return tipoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public TipoDTO buscarPorId(Integer id){
        log.info("Buscando tipo con ID: {}", id);
        Tipo tipo = tipoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tipo de producto no encontrado."));
        return convertirADTO(tipo);
    } 

    public TipoDTO guardar(Tipo tipo){
        log.info("Guardando nuevo tipo: {}", tipo.getNombreTipo());
        Tipo guardado = tipoRepository.save(tipo);
        return convertirADTO(guardado);
    }

    public String eliminar(Integer id){
        log.info("Eliminando tipo con ID: {}", id);
        try {
            Tipo tipo = tipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imposible eliminar, el tipo de producto con ID " + id + " no existe."));
            tipoRepository.delete(tipo);
            return "El tipo de producto " + tipo.getNombreTipo() + " ha sido eliminado.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    private TipoDTO convertirADTO(Tipo tipo){
        TipoDTO dto = new TipoDTO();
        dto.setNombreTipo(tipo.getNombreTipo());
        dto.setTipoId(tipo.getTipoId());
        return dto;
    }
}
