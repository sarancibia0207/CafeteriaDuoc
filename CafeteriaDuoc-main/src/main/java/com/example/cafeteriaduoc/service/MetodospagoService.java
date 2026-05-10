package com.example.cafeteriaduoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.model.Metodospago;
import com.example.cafeteriaduoc.repository.MetodospagoRepository;

@Service
@Transactional
public class MetodospagoService {
    @Autowired
    private MetodospagoRepository metodospagoRepository;

    // Añadir? No sé si va esto. Comentar en cualquier caso.
    public String anadirMetodosPago(Metodospago metodospagos){
        metodospagoRepository.save(metodospagos);
        return "La boleta " + metodospagos.getVentas().getVentaId() + " ha sido asignada el metodo de pago " + metodospagos.getMetodopago().getTipoMetodoPago();
    }

    public List<Metodospago> obtenerTodos(){
        return metodospagoRepository.findAll();
    }
}
