package com.example.cafeteriaduoc.mscliente.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.cafeteriaduoc.mscliente.DTO.ClienteDTO;
import com.example.cafeteriaduoc.mscliente.model.Cliente;
import com.example.cafeteriaduoc.mscliente.repository.ClienteRepository;
import com.example.cafeteriaduoc.mscliente.service.ClienteService;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private final Faker faker = new Faker();

    private Cliente crearCliente() {
        Cliente cliente = new Cliente();
        cliente.setClienteId(faker.number().numberBetween(1, 100));
        cliente.setNombreCliente(faker.name().fullName());
        cliente.setRutCliente(faker.idNumber().valid());
        cliente.setFechaNacimiento(Date.valueOf("1990-01-01"));
        cliente.setPuntosAcumulables(faker.number().randomDouble(2, 10, 500));
        return cliente;
    }

    @Test
    public void testObtenerTodos() {
        Cliente cliente = crearCliente();
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<ClienteDTO> resultado = clienteService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(cliente.getNombreCliente(), resultado.get(0).getNombreCliente());
    }

    @Test
    public void testBuscarPorId() {
        Cliente cliente = crearCliente();
        Integer id = cliente.getClienteId();
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        ClienteDTO resultado = clienteService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getClienteId());
        assertEquals(cliente.getNombreCliente(), resultado.getNombreCliente());
        verify(clienteRepository, times(1)).findById(id);
    }

    @Test
    public void testGuardar() {
        Cliente nuevoCliente = crearCliente();
        when(clienteRepository.save(any(Cliente.class))).thenReturn(nuevoCliente);

        ClienteDTO resultado = clienteService.guardar(nuevoCliente);

        assertNotNull(resultado);
        assertEquals(nuevoCliente.getNombreCliente(), resultado.getNombreCliente());
        verify(clienteRepository, times(1)).save(nuevoCliente);
    }

    @Test
    public void testEliminar() {
        Cliente cliente = crearCliente();
        Integer id = cliente.getClienteId();
        
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));
        doNothing().when(clienteRepository).delete(cliente);

        String resultado = clienteService.eliminar(id);

        assertTrue(resultado.contains("ha sido eliminado"));
        verify(clienteRepository, times(1)).delete(cliente);
    }

    @Test
    public void testBuscarPorNombre() {
        Cliente cliente = crearCliente();
        String nombre = cliente.getNombreCliente();
        when(clienteRepository.findBynombreCliente(nombre)).thenReturn(java.util.Optional.of(cliente));

        List<ClienteDTO> resultado = clienteService.buscarPorNombre(nombre);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(nombre, resultado.get(0).getNombreCliente());
    }

    @Test
    public void testActualizar() {
        Cliente clienteExistente = crearCliente();
        Integer id = clienteExistente.getClienteId();
        Cliente clienteNuevosDatos = crearCliente(); 
        
        when(clienteRepository.findById(id)).thenReturn(Optional.of(clienteExistente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteExistente);

        ClienteDTO resultado = clienteService.actualizar(id, clienteNuevosDatos);

        assertNotNull(resultado);
        assertEquals(clienteNuevosDatos.getNombreCliente(), resultado.getNombreCliente());
        verify(clienteRepository, times(1)).save(clienteExistente);
    }

    @Test
    public void testPatchCliente() {
        Cliente clienteExistente = crearCliente();
        Integer id = clienteExistente.getClienteId();
        
        Cliente clienteParcial = new Cliente();
        clienteParcial.setNombreCliente("Nombre Parcheado");
        
        when(clienteRepository.findById(id)).thenReturn(Optional.of(clienteExistente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteExistente);

        ClienteDTO resultado = clienteService.patchCliente(id, clienteParcial);

        assertNotNull(resultado);
        assertEquals("Nombre Parcheado", resultado.getNombreCliente());
        verify(clienteRepository, times(1)).save(clienteExistente);
    }
}