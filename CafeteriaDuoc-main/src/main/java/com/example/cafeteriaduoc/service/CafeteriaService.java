package com.example.cafeteriaduoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.model.Cafeteria;
import com.example.cafeteriaduoc.repository.CafeteriaRepository;

@Service
@Transactional
public class CafeteriaService {
    @Autowired
    private CafeteriaRepository cafeteriaRepository;

    public List<Cafeteria> obtenerTodos(){
        return cafeteriaRepository.findAll();
    }

    public Cafeteria buscarPorId(Integer id){
        Cafeteria cafeteria = cafeteriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cafeteria no encontrada."));
        return cafeteria;
    } // Esto no sé si va

    public Cafeteria guardar(Cafeteria cafeteria){
        return cafeteriaRepository.save(cafeteria);
    }

    public String eliminar(Integer id){
        try {
            Cafeteria cafeteria = cafeteriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imposible eliminar, la cafeteria con ID " + id + " no existe."));
            cafeteriaRepository.delete(cafeteria);
            return "La cafeteria " + cafeteria.getNombreCafeteria() + " ha sido eliminada.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Cafeteria actualizarCafeteria(Integer id, Cafeteria cafeteria){
        Cafeteria cafeteria2 = cafeteriaRepository.findById(id).orElseThrow(() -> new RuntimeException("La cafeteria no existe."));
        if(cafeteria.getNombreCafeteria() != null){
            cafeteria2.setNombreCafeteria(cafeteria.getNombreCafeteria());
        }
        if(cafeteria.getComuna() != null){
            cafeteria2.setComuna(cafeteria.getComuna());
        }
        return cafeteriaRepository.save(cafeteria2);
    }
}
