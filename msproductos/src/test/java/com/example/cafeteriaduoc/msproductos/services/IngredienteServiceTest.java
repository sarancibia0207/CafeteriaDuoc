package com.example.cafeteriaduoc.msproductos.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.cafeteriaduoc.msproductos.DTO.IngredienteDTO;
import com.example.cafeteriaduoc.msproductos.model.Ingrediente;
import com.example.cafeteriaduoc.msproductos.repository.IngredienteRepository;
import com.example.cafeteriaduoc.msproductos.service.IngredienteService;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
public class IngredienteServiceTest {

    @Mock
    private IngredienteRepository ingredienteRepository;

    @InjectMocks
    private IngredienteService ingredienteService;

    private final Faker faker = new Faker();

    private Ingrediente crearIngrediente() {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setIngredienteId(faker.number().numberBetween(1, 100));
        ingrediente.setNombreIngrediente(faker.food().ingredient()); 
        ingrediente.setCantidadIngrediente(faker.number().randomDouble(2, 10, 1000)); 
        return ingrediente;
    }

    @Test
    public void testObtenerTodas() {
        Ingrediente ingrediente = crearIngrediente();
        when(ingredienteRepository.findAll()).thenReturn(List.of(ingrediente));

        List<IngredienteDTO> resultado = ingredienteService.obtenerTodas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(ingrediente.getNombreIngrediente(), resultado.get(0).getNombreIngrediente());
    }

    @Test
    public void testBuscarPorId() {
        Ingrediente ingrediente = crearIngrediente();
        Integer id = ingrediente.getIngredienteId();
        when(ingredienteRepository.findById(id)).thenReturn(Optional.of(ingrediente));

        IngredienteDTO resultado = ingredienteService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getIngredienteId());
        assertEquals(ingrediente.getNombreIngrediente(), resultado.getNombreIngrediente());
        verify(ingredienteRepository, times(1)).findById(id);
    }

    @Test
    public void testGuardar() {
        Ingrediente nuevoIngrediente = crearIngrediente();
        when(ingredienteRepository.save(any(Ingrediente.class))).thenReturn(nuevoIngrediente);

        IngredienteDTO resultado = ingredienteService.guardar(nuevoIngrediente);

        assertNotNull(resultado);
        assertEquals(nuevoIngrediente.getNombreIngrediente(), resultado.getNombreIngrediente());
        verify(ingredienteRepository, times(1)).save(nuevoIngrediente);
    }

    @Test
    public void testEliminar() {
        Ingrediente ingrediente = crearIngrediente();
        Integer id = ingrediente.getIngredienteId();
        
        when(ingredienteRepository.findById(id)).thenReturn(Optional.of(ingrediente));
        doNothing().when(ingredienteRepository).delete(ingrediente);

        String resultado = ingredienteService.eliminar(id);

        assertTrue(resultado.contains("ha sido eliminado"));
        verify(ingredienteRepository, times(1)).delete(ingrediente);
    }

    @Test
    public void testActualizarIngrediente() {
        Ingrediente ingredienteExistente = crearIngrediente();
        Integer id = ingredienteExistente.getIngredienteId();
        Ingrediente ingredienteNuevosDatos = crearIngrediente(); 
        
        when(ingredienteRepository.findById(id)).thenReturn(Optional.of(ingredienteExistente));
        when(ingredienteRepository.save(any(Ingrediente.class))).thenReturn(ingredienteExistente);

        IngredienteDTO resultado = ingredienteService.actualizarIngrediente(id, ingredienteNuevosDatos);

        assertNotNull(resultado);
        assertEquals(ingredienteNuevosDatos.getNombreIngrediente(), resultado.getNombreIngrediente());
        verify(ingredienteRepository, times(1)).save(ingredienteExistente);
    }

    @Test
    public void testPatchIngrediente() {
        Ingrediente ingredienteExistente = crearIngrediente();
        Integer id = ingredienteExistente.getIngredienteId();
        
        Ingrediente ingredienteParcial = new Ingrediente();
        ingredienteParcial.setNombreIngrediente("Azúcar Parcheada");
        
        when(ingredienteRepository.findById(id)).thenReturn(Optional.of(ingredienteExistente));
        when(ingredienteRepository.save(any(Ingrediente.class))).thenReturn(ingredienteExistente);

        IngredienteDTO resultado = ingredienteService.patchIngrediente(id, ingredienteParcial);

        assertNotNull(resultado);
        assertEquals("Azúcar Parcheada", resultado.getNombreIngrediente());
        verify(ingredienteRepository, times(1)).save(ingredienteExistente);
    }
}