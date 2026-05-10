package com.example.cafeteriaduoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import com.example.cafeteriaduoc.DTO.ClienteDTO;
import com.example.cafeteriaduoc.model.Cliente;
import com.example.cafeteriaduoc.repository.ClienteRepository;

@Service
@Transactional
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> obtenerTodos(){
        return clienteRepository.findAll().stream()
                .map(this::convertirADTO) // Transmutamos cada cliente
                .toList();
    }

    public ClienteDTO buscarPorId(Integer clienteId){
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("El cliente no existe en los registros."));
        return convertirADTO(cliente); // Transmutamos el cliente encontrado
    }

    // public Cliente guardar(Cliente cliente){
    //     return clienteRepository.save(cliente);
    // }

    public ClienteDTO guardar(Cliente nuevoCliente){
        Cliente clienteGuardado = clienteRepository.save(nuevoCliente);
        return convertirADTO(clienteGuardado);
    }

    public String eliminar(Integer id){
        try {
            Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("¡Imposible eliminar! Cliente con ID " + id + " no existe."));
            clienteRepository.delete(cliente);
            return "El cliente '" + cliente.getNombreCliente() + "' ha sido eliminado.";
        } catch (RuntimeException e) {
           return e.getMessage();
        }
    } // Creo que por leyes de protección de datos, si se debería poder borrar un cliente.

    // actualizar?
    
    private ClienteDTO convertirADTO(Cliente cliente){
        ClienteDTO dto = new ClienteDTO();
        dto.setClienteId(cliente.getClienteId());
        dto.setNombreCliente(cliente.getNombreCliente());
        dto.setPuntosAcumulables(cliente.getPuntosAcumulables());
        return dto;
    }
}