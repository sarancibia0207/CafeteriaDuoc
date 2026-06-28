package com.example.cafeteriaduoc.msventa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.msventa.model.Metodospago;
import com.example.cafeteriaduoc.msventa.repository.MetodospagoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MetodospagoService {
    @Autowired
    private MetodospagoRepository metodospagoRepository;

    public List<Metodospago> obtenerTodos(){
        log.info("Obteniendo todos los métodos de pago de ventas");
        return metodospagoRepository.findAll();
    }

    public Metodospago guardar(Metodospago metodospago) {
        log.info("Guardando método de pago para venta");
        return metodospagoRepository.save(metodospago);
    }
}