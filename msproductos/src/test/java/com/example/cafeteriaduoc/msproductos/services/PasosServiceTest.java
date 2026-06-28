package com.example.cafeteriaduoc.msproductos.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.cafeteriaduoc.msproductos.model.Pasos;
import com.example.cafeteriaduoc.msproductos.repository.PasosRepository;
import com.example.cafeteriaduoc.msproductos.service.PasosService;

@ExtendWith(MockitoExtension.class)
public class PasosServiceTest {

    @Mock
    private PasosRepository pasosRepository;

    @InjectMocks
    private PasosService pasosService;

    @Test
    public void testObtenerTodos() {
        Pasos pasoMock = new Pasos();
        when(pasosRepository.findAll()).thenReturn(List.of(pasoMock));
        List<Pasos> resultado = pasosService.obtenerTodos();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(pasosRepository, times(1)).findAll();
    }
}