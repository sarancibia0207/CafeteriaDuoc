package com.example.cafeteriaduoc.msventa.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.cafeteriaduoc.msventa.model.Metodospago;
import com.example.cafeteriaduoc.msventa.repository.MetodospagoRepository;
import com.example.cafeteriaduoc.msventa.service.MetodospagoService;

@ExtendWith(MockitoExtension.class)
public class MetodosPagoServiceTest {

    @Mock
    private MetodospagoRepository metodospagoRepository;

    @InjectMocks
    private MetodospagoService metodospagoService;

    @Test
    public void testObtenerTodos() {
        Metodospago relacionMock = new Metodospago();

        when(metodospagoRepository.findAll()).thenReturn(List.of(relacionMock));

        List<Metodospago> resultado = metodospagoService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(metodospagoRepository, times(1)).findAll();
    }

    @Test
    public void testGuardar() {
        Metodospago relacionMock = new Metodospago();
        
        when(metodospagoRepository.save(any(Metodospago.class))).thenReturn(relacionMock);

        Metodospago resultado = metodospagoService.guardar(relacionMock);

        assertNotNull(resultado);
        verify(metodospagoRepository, times(1)).save(relacionMock);
    }
}