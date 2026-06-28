package com.example.cafeteriaduoc.msventa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.msventa.DTO.MetodoPagoDTO;
import com.example.cafeteriaduoc.msventa.model.Metodopago;
import com.example.cafeteriaduoc.msventa.repository.MetodopagoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MetodopagoService {
    @Autowired
    private MetodopagoRepository metodopagoRepository;
    
    public List<MetodoPagoDTO> obtenerTodos(){
        log.info("Obteniendo todos los métodos de pago");
        return metodopagoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public MetodoPagoDTO buscarPorId(Integer id){
        log.info("Buscando método de pago con ID: {}", id);
        Metodopago metodopago = metodopagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El metodo de pago no existe!"));
        return convertirADTO(metodopago);
    }

    private MetodoPagoDTO convertirADTO(Metodopago metodoPago){
        MetodoPagoDTO dto = new MetodoPagoDTO();
        dto.setMetodoPagoId(metodoPago.getMetodopagoId());
        dto.setTipoMetodoPago(metodoPago.getTipoMetodoPago());
        return dto;
    }
}