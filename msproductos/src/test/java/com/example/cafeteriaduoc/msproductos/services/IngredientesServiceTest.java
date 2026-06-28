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

import com.example.cafeteriaduoc.msproductos.model.Ingredientes;
import com.example.cafeteriaduoc.msproductos.repository.IngredientesRepository;
import com.example.cafeteriaduoc.msproductos.service.IngredientesService;

@ExtendWith(MockitoExtension.class)
public class IngredientesServiceTest {

    @Mock
    private IngredientesRepository ingredientesRepository;

    @InjectMocks
    private IngredientesService ingredientesService;

    @Test
    public void testObtenerTodos() {
        Ingredientes relacionMock = new Ingredientes();
        
        when(ingredientesRepository.findAll()).thenReturn(List.of(relacionMock));

        List<Ingredientes> resultado = ingredientesService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(ingredientesRepository, times(1)).findAll();
    }
}