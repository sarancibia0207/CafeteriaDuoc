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

import com.example.cafeteriaduoc.msproductos.model.Tipos;
import com.example.cafeteriaduoc.msproductos.repository.TiposRepository;
import com.example.cafeteriaduoc.msproductos.service.TiposService;

@ExtendWith(MockitoExtension.class)
public class TiposServiceTest {

    @Mock
    private TiposRepository tiposRepository;

    @InjectMocks
    private TiposService tiposService;

    @Test
    public void testObtenerTodos() {
        Tipos relacionTiposMock = new Tipos();

        when(tiposRepository.findAll()).thenReturn(List.of(relacionTiposMock));

        List<Tipos> resultado = tiposService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());

        verify(tiposRepository, times(1)).findAll();
    }
}