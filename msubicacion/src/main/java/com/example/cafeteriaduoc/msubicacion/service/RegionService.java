package com.example.cafeteriaduoc.msubicacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.msubicacion.DTO.RegionDTO;
import com.example.cafeteriaduoc.msubicacion.model.Region;
import com.example.cafeteriaduoc.msubicacion.repository.RegionRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public List<RegionDTO> obtenerTodos() {
        log.info("Obteniendo todas las regiones");
        return regionRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    } 

    public RegionDTO buscarPorId(Integer id){
        log.info("Buscando región con ID: {}", id);
        Region region = regionRepository.findById(id).orElseThrow(() -> new RuntimeException("Region no encontrada."));
        return convertirADTO(region);
    }

    public RegionDTO guardar(Region region){
        log.info("Guardando nueva región: {}", region.getNombreRegion());
        Region guardado = regionRepository.save(region);
        return convertirADTO(guardado);
    }

    private RegionDTO convertirADTO(Region region){
        RegionDTO dto = new RegionDTO();
        dto.setNombreRegion(region.getNombreRegion());
        dto.setRegionId(region.getRegionId());
        return dto;
    }
}
