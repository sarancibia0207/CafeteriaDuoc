package com.example.cafeteriaduoc.msubicacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.msubicacion.DTO.CafeteriaDTO;
import com.example.cafeteriaduoc.msubicacion.model.Cafeteria;
import com.example.cafeteriaduoc.msubicacion.repository.CafeteriaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CafeteriaService {
    @Autowired
    private CafeteriaRepository cafeteriaRepository;

    public List<CafeteriaDTO> obtenerTodos(){
        log.info("Obteniendo todas las cafeterías");
        return cafeteriaRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public CafeteriaDTO buscarPorId(Integer id){
        log.info("Buscando cafetería con ID: {}", id);
        Cafeteria cafeteria = cafeteriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cafeteria no encontrada."));
        return convertirADTO(cafeteria);
    } // Esto no sé si va

    public CafeteriaDTO guardar(Cafeteria cafeteria){
        log.info("Guardando nueva cafetería: {}", cafeteria.getNombreCafeteria());
        Cafeteria guardado = cafeteriaRepository.save(cafeteria);
        return convertirADTO(guardado);
    }

    public String eliminar(Integer id){
        log.info("Eliminando cafetería con ID: {}", id);
        try {
            Cafeteria cafeteria = cafeteriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imposible eliminar, la cafeteria con ID " + id + " no existe."));
            cafeteriaRepository.delete(cafeteria);
            return "La cafeteria " + cafeteria.getNombreCafeteria() + " ha sido eliminada.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public CafeteriaDTO actualizarCafeteria(Integer id, Cafeteria cafeteriaActualizada) {
        log.info("Actualizando cafetería con ID: {}", id);
        Cafeteria cafeteria = cafeteriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La cafetería con ID " + id + " no existe."));
        cafeteria.setNombreCafeteria(cafeteriaActualizada.getNombreCafeteria());
        cafeteria.setComuna(cafeteriaActualizada.getComuna());
        return convertirADTO(cafeteriaRepository.save(cafeteria));
    }

    public CafeteriaDTO patchCafeteria(Integer id, Cafeteria cafeteriaActualizada) {
        log.info("Actualizando parcialmente cafetería con ID: {}", id);
        Cafeteria cafeteria = cafeteriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La cafetería con ID " + id + " no existe."));
        if (cafeteriaActualizada.getNombreCafeteria() != null) cafeteria.setNombreCafeteria(cafeteriaActualizada.getNombreCafeteria());
        if (cafeteriaActualizada.getComuna() != null) cafeteria.setComuna(cafeteriaActualizada.getComuna());
        return convertirADTO(cafeteriaRepository.save(cafeteria));
    }

    private CafeteriaDTO convertirADTO(Cafeteria cafeteria){
        CafeteriaDTO dto = new CafeteriaDTO();
        dto.setCafeteriaId(cafeteria.getCafeteriaId());
        dto.setNombreCafeteria(cafeteria.getNombreCafeteria());
        dto.setNombreComuna(cafeteria.getComuna().getNombreComuna());
        return dto;
    }
}
