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

import com.example.cafeteriaduoc.msproductos.model.TiposIngredientes;
import com.example.cafeteriaduoc.msproductos.repository.TiposIngredientesRepository;
import com.example.cafeteriaduoc.msproductos.service.TiposIngredienteService;

@ExtendWith(MockitoExtension.class)
public class TiposIngredienteServiceTest {

    @Mock
    private TiposIngredientesRepository tiposIngredientesRepository;

    @InjectMocks
    private TiposIngredienteService tiposIngredienteService;

    @Test
    public void testObtenerTodos() {
        TiposIngredientes relacionTipoMock = new TiposIngredientes();

        when(tiposIngredientesRepository.findAll()).thenReturn(List.of(relacionTipoMock));

        List<TiposIngredientes> resultado = tiposIngredienteService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());

        verify(tiposIngredientesRepository, times(1)).findAll();
    }
}