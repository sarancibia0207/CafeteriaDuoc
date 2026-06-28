package com.example.cafeteriaduoc.mscliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import com.example.cafeteriaduoc.mscliente.DTO.ClienteDTO;
import com.example.cafeteriaduoc.mscliente.model.Cliente;
import com.example.cafeteriaduoc.mscliente.repository.ClienteRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> obtenerTodos(){
        log.info("Obteniendo todos los clientes");
        return clienteRepository.findAll().stream()
                .map(this::convertirADTO) 
                .toList();
    }

    public ClienteDTO buscarPorId(Integer clienteId){
        log.info("Buscando cliente con ID: {}", clienteId);
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("El cliente no existe en los registros."));
        return convertirADTO(cliente); 
    }

    public ClienteDTO guardar(Cliente nuevoCliente){
        log.info("Guardando nuevo cliente: {}", nuevoCliente.getNombreCliente());
        Cliente clienteGuardado = clienteRepository.save(nuevoCliente);
        return convertirADTO(clienteGuardado);
    }

    public String eliminar(Integer id){
        log.info("Eliminando cliente con ID: {}", id);
        try {
            Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("¡Imposible eliminar! Cliente con ID " + id + " no existe."));
            clienteRepository.delete(cliente);
            return "El cliente '" + cliente.getNombreCliente() + "' ha sido eliminado.";
        } catch (RuntimeException e) {
           return e.getMessage();
        }
    }

    public List<ClienteDTO> buscarPorNombre(String nombre){
        log.info("Buscando clientes con nombre: {}", nombre);
        return clienteRepository.findBynombreCliente(nombre).stream()
                .map(this::convertirADTO)
                .toList();
    }
    
    private ClienteDTO convertirADTO(Cliente cliente){
        ClienteDTO dto = new ClienteDTO();
        dto.setClienteId(cliente.getClienteId());
        dto.setNombreCliente(cliente.getNombreCliente());
        dto.setPuntosAcumulables(cliente.getPuntosAcumulables());
        return dto;
    }

    public ClienteDTO actualizar(Integer id, Cliente clienteActualizado) {
        log.info("Actualizando cliente con ID: {}", id);
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El cliente con ID " + id + " no existe."));
        cliente.setNombreCliente(clienteActualizado.getNombreCliente());
        cliente.setRutCliente(clienteActualizado.getRutCliente());
        cliente.setFechaNacimiento(clienteActualizado.getFechaNacimiento());
        cliente.setPuntosAcumulables(clienteActualizado.getPuntosAcumulables());
        return convertirADTO(clienteRepository.save(cliente));
    }

    public ClienteDTO patchCliente(Integer id, Cliente clienteParcial) {
        log.info("Actualizando parcialmente cliente con ID: {}", id);
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El cliente con ID " + id + " no existe."));
        if (clienteParcial.getNombreCliente() != null) cliente.setNombreCliente(clienteParcial.getNombreCliente());
        if (clienteParcial.getRutCliente() != null) cliente.setRutCliente(clienteParcial.getRutCliente());
        if (clienteParcial.getFechaNacimiento() != null) cliente.setFechaNacimiento(clienteParcial.getFechaNacimiento());
        if (clienteParcial.getPuntosAcumulables() != 0) cliente.setPuntosAcumulables(clienteParcial.getPuntosAcumulables());
        return convertirADTO(clienteRepository.save(cliente));
    }
}