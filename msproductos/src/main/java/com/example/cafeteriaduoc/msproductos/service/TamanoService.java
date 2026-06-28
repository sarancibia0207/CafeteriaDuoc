package com.example.cafeteriaduoc.msproductos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.msproductos.DTO.TamanoDTO;
import com.example.cafeteriaduoc.msproductos.model.Tamano;
import com.example.cafeteriaduoc.msproductos.repository.TamanoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class TamanoService {
    @Autowired
    private TamanoRepository tamanoRepository;

    public List<TamanoDTO> obtenerTodos(){
        log.info("Obteniendo todos los tamaños");
        return tamanoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public TamanoDTO buscarPorId(Integer id){
        log.info("Buscando tamaño con ID: {}", id);
        Tamano tamano = tamanoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tamano no encontrado."));
        return convertirADTO(tamano);
    } 

    public TamanoDTO guardar(Tamano tamano){
        log.info("Guardando nuevo tamaño: {}", tamano.getNombreTamano());
        Tamano guardado = tamanoRepository.save(tamano);
        return convertirADTO(guardado);
    }

    public String eliminar(Integer id){
        log.info("Eliminando tamaño con ID: {}", id);
        try {
            Tamano tamano = tamanoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imposible eliminar, el tamano con ID " + id + " no existe."));
            tamanoRepository.delete(tamano);
            return "El tamano " + tamano.getNombreTamano() + " ha sido eliminado.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    private TamanoDTO convertirADTO(Tamano tamano){
        TamanoDTO dto = new TamanoDTO();
        dto.setNombreTamano(tamano.getNombreTamano());
        dto.setTamanoId(tamano.getTamanoId());
        return dto;
    }
}
