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

import com.example.cafeteriaduoc.msproductos.DTO.TamanoDTO;
import com.example.cafeteriaduoc.msproductos.model.Tamano;
import com.example.cafeteriaduoc.msproductos.repository.TamanoRepository;
import com.example.cafeteriaduoc.msproductos.service.TamanoService;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
public class TamanoServiceTest {

    @Mock
    private TamanoRepository tamanoRepository;

    @InjectMocks
    private TamanoService tamanoService;

    private final Faker faker = new Faker();

    private Tamano crearTamano() {
        Tamano tamano = new Tamano();
        tamano.setTamanoId(faker.number().numberBetween(1, 10));
        tamano.setNombreTamano(faker.options().option("Pequeño", "Mediano", "Grande", "Venti")); 
        return tamano;
    }

    @Test
    public void testObtenerTodos() {
        Tamano tamano = crearTamano();
        when(tamanoRepository.findAll()).thenReturn(List.of(tamano));

        List<TamanoDTO> resultado = tamanoService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(tamano.getNombreTamano(), resultado.get(0).getNombreTamano());
    }

    @Test
    public void testBuscarPorId() {
        Tamano tamano = crearTamano();
        Integer id = tamano.getTamanoId();
        when(tamanoRepository.findById(id)).thenReturn(Optional.of(tamano));

        TamanoDTO resultado = tamanoService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getTamanoId());
        assertEquals(tamano.getNombreTamano(), resultado.getNombreTamano());
        verify(tamanoRepository, times(1)).findById(id);
    }

    @Test
    public void testGuardar() {
        Tamano nuevoTamano = crearTamano();
        when(tamanoRepository.save(any(Tamano.class))).thenReturn(nuevoTamano);

        TamanoDTO resultado = tamanoService.guardar(nuevoTamano);

        assertNotNull(resultado);
        assertEquals(nuevoTamano.getNombreTamano(), resultado.getNombreTamano());
        verify(tamanoRepository, times(1)).save(nuevoTamano);
    }

    @Test
    public void testEliminar() {
        Tamano tamano = crearTamano();
        Integer id = tamano.getTamanoId();
        
        when(tamanoRepository.findById(id)).thenReturn(Optional.of(tamano));
        doNothing().when(tamanoRepository).delete(tamano);

        String resultado = tamanoService.eliminar(id);

        assertTrue(resultado.contains("ha sido eliminado"));
        verify(tamanoRepository, times(1)).delete(tamano);
    }
}