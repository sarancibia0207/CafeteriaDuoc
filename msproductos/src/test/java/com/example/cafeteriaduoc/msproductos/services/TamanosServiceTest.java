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

import com.example.cafeteriaduoc.msproductos.model.Tamanos;
import com.example.cafeteriaduoc.msproductos.repository.TamanosRepository;
import com.example.cafeteriaduoc.msproductos.service.TamanosService;

@ExtendWith(MockitoExtension.class)
public class TamanosServiceTest {

    @Mock
    private TamanosRepository tamanosRepository;

    @InjectMocks
    private TamanosService tamanosService;

    @Test
    public void testObtenerTodos() {
        Tamanos relacionTamanoMock = new Tamanos();
        when(tamanosRepository.findAll()).thenReturn(List.of(relacionTamanoMock));
        List<Tamanos> resultado = tamanosService.obtenerTodos();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(tamanosRepository, times(1)).findAll();
    }
}