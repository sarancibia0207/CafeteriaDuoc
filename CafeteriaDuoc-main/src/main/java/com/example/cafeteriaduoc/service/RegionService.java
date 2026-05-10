package com.example.cafeteriaduoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.model.Region;
import com.example.cafeteriaduoc.repository.RegionRepository;

@Service
@Transactional
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public List<Region> obtenerTodos() {
        return regionRepository.findAll();
    } 

    public Region guardar(Region region){
        return regionRepository.save(region);
    }

    // Al parecer, según el código del profe, este Service quedaría así.
}
