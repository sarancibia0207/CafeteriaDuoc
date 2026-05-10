package com.example.cafeteriaduoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.model.Metodopago;
import com.example.cafeteriaduoc.repository.MetodopagoRepository;

@Service
@Transactional
public class MetodopagoService {
    @Autowired
    private MetodopagoRepository metodopagoRepository;
    
    public List<Metodopago> obtenerTodos(){
        return metodopagoRepository.findAll();
    }

    public Metodopago guardar(Metodopago metodopago){
        return metodopagoRepository.save(metodopago);
    }

    public String eliminar(Integer id){
        try {
            Metodopago metodopago = metodopagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imposible eliminar, el metodo de pago con ID " + id + " no existe."));
            metodopagoRepository.delete(metodopago);
            return "El metodo de pago  " + metodopago.getTipoMetodoPago() + " ha sido eliminado.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
