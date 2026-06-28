package com.example.cafeteriaduoc.msubicacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.example.cafeteriaduoc.msubicacion.DTO.ComunaDTO;
import com.example.cafeteriaduoc.msubicacion.model.Comuna;
import com.example.cafeteriaduoc.msubicacion.repository.ComunaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ComunaService {
    @Autowired
    private ComunaRepository comunaRepository;

    public List<ComunaDTO> obtenerTodos() {
        log.info("Obteniendo todas las comunas");
        return comunaRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    } 

    public ComunaDTO buscarPorId(Integer id){
        log.info("Buscando comuna con ID: {}", id);
        Comuna comuna = comunaRepository.findById(id).orElseThrow(() -> new RuntimeException("Comuna no encontrada."));
        return convertirADTO(comuna);
    }

    public ComunaDTO guardar(Comuna comuna){
        log.info("Guardando nueva comuna: {}", comuna.getNombreComuna());
        Comuna guardado = comunaRepository.save(comuna);
        return convertirADTO(guardado);
    }

    private ComunaDTO convertirADTO(Comuna comuna){
        ComunaDTO dto = new ComunaDTO();
        dto.setComunaId(comuna.getComunaId());
        dto.setNombreComuna(comuna.getNombreComuna());
        dto.setNombreRegion(comuna.getRegion().getNombreRegion());
        return dto;
    }
}