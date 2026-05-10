package com.example.cafeteriaduoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.model.Tamano;
import com.example.cafeteriaduoc.repository.TamanoRepository;

@Service
@Transactional
public class TamanoService {
    @Autowired
    private TamanoRepository tamanoRepository;

    public List<Tamano> obtenerTodos(){
        return tamanoRepository.findAll();
    }

    public Tamano buscarPorId(Integer id){
        Tamano tamano = tamanoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tamano no encontrado."));
        return tamano;
    } // Esto no sé si va

    public Tamano guardar(Tamano tamano){
        return tamanoRepository.save(tamano);
    }

    public String eliminar(Integer id){
        try {
            Tamano tamano = tamanoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imposible eliminar, el tamano con ID " + id + " no existe."));
            tamanoRepository.delete(tamano);
            return "El tamano " + tamano.getNombreTamano() + " ha sido eliminado.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    // public Tamano actualizar(Integer id, Tamano tamano2){
    //     Tamano tamano2 = tamanoRepository.findById(id).orElseThrow(() -> new RuntimeException("La cafeteria no existe."));
    //     if(tamano2.getNombreTamano() != null){
    //         tamano2.setNombreTamano(tamano2.getNombreTamano());
    //     }
    //     return tamanoRepository.save(tamano2);
    // } Creo que esto está de más
}
