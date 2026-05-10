package com.example.cafeteriaduoc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.example.cafeteriaduoc.model.Comuna;
import com.example.cafeteriaduoc.repository.ComunaRepository;

@Service
@Transactional
public class ComunaService {
    @Autowired
    private ComunaRepository comunaRepository;

    public List<Comuna> obtenerTodos() {
        return comunaRepository.findAll();
    } 

    public Comuna guardar(Comuna comuna){
        return comunaRepository.save(comuna);
    }

    // Al parecer, según el código del profe, este Service quedaría así.
}