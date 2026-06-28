package com.example.cafeteriaduoc.msubicacion.services;

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

import com.example.cafeteriaduoc.msubicacion.DTO.CafeteriaDTO;
import com.example.cafeteriaduoc.msubicacion.model.Cafeteria;
import com.example.cafeteriaduoc.msubicacion.model.Comuna;
import com.example.cafeteriaduoc.msubicacion.repository.CafeteriaRepository;
import com.example.cafeteriaduoc.msubicacion.service.CafeteriaService;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
public class CafeteriaServiceTest {

    @Mock
    private CafeteriaRepository cafeteriaRepository;

    @InjectMocks
    private CafeteriaService cafeteriaService;

    private final Faker faker = new Faker();

    private Cafeteria crearCafeteria() {
        Cafeteria cafeteria = new Cafeteria();
        cafeteria.setCafeteriaId(faker.number().numberBetween(1, 50));
        cafeteria.setNombreCafeteria(faker.company().name() + " Café");

        Comuna comuna = new Comuna();
        comuna.setComunaId(faker.number().numberBetween(1, 300));
        comuna.setNombreComuna(faker.address().cityName());
        cafeteria.setComuna(comuna);

        return cafeteria;
    }

    @Test
    public void testObtenerTodos() {
        Cafeteria cafeteria = crearCafeteria();
        when(cafeteriaRepository.findAll()).thenReturn(List.of(cafeteria));

        List<CafeteriaDTO> resultado = cafeteriaService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(cafeteria.getNombreCafeteria(), resultado.get(0).getNombreCafeteria());
        assertEquals(cafeteria.getComuna().getNombreComuna(), resultado.get(0).getNombreComuna());
    }

    @Test
    public void testBuscarPorId() {
        Cafeteria cafeteria = crearCafeteria();
        Integer id = cafeteria.getCafeteriaId();
        when(cafeteriaRepository.findById(id)).thenReturn(Optional.of(cafeteria));

        CafeteriaDTO resultado = cafeteriaService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getCafeteriaId());
        assertEquals(cafeteria.getNombreCafeteria(), resultado.getNombreCafeteria());
        verify(cafeteriaRepository, times(1)).findById(id);
    }

    @Test
    public void testGuardar() {
        Cafeteria nuevaCafeteria = crearCafeteria();
        when(cafeteriaRepository.save(any(Cafeteria.class))).thenReturn(nuevaCafeteria);

        CafeteriaDTO resultado = cafeteriaService.guardar(nuevaCafeteria);

        assertNotNull(resultado);
        assertEquals(nuevaCafeteria.getNombreCafeteria(), resultado.getNombreCafeteria());
        verify(cafeteriaRepository, times(1)).save(nuevaCafeteria);
    }

    @Test
    public void testEliminar() {
        Cafeteria cafeteria = crearCafeteria();
        Integer id = cafeteria.getCafeteriaId();
        
        when(cafeteriaRepository.findById(id)).thenReturn(Optional.of(cafeteria));
        doNothing().when(cafeteriaRepository).delete(cafeteria);

        String resultado = cafeteriaService.eliminar(id);

        assertTrue(resultado.contains("ha sido eliminada"));
        verify(cafeteriaRepository, times(1)).delete(cafeteria);
    }

    @Test
    public void testActualizarCafeteria() {
        Cafeteria cafeteriaExistente = crearCafeteria();
        Integer id = cafeteriaExistente.getCafeteriaId();
        Cafeteria cafeteriaNuevosDatos = crearCafeteria(); 
        
        when(cafeteriaRepository.findById(id)).thenReturn(Optional.of(cafeteriaExistente));
        when(cafeteriaRepository.save(any(Cafeteria.class))).thenReturn(cafeteriaExistente);

        CafeteriaDTO resultado = cafeteriaService.actualizarCafeteria(id, cafeteriaNuevosDatos);

        assertNotNull(resultado);
        assertEquals(cafeteriaNuevosDatos.getNombreCafeteria(), resultado.getNombreCafeteria());
        verify(cafeteriaRepository, times(1)).save(cafeteriaExistente);
    }

    @Test
    public void testPatchCafeteria() {
        Cafeteria cafeteriaExistente = crearCafeteria();
        Integer id = cafeteriaExistente.getCafeteriaId();
        
        Cafeteria cafeteriaParcial = new Cafeteria();
        cafeteriaParcial.setNombreCafeteria("Sede Central Café");
        
        when(cafeteriaRepository.findById(id)).thenReturn(Optional.of(cafeteriaExistente));
        when(cafeteriaRepository.save(any(Cafeteria.class))).thenReturn(cafeteriaExistente);

        CafeteriaDTO resultado = cafeteriaService.patchCafeteria(id, cafeteriaParcial);

        assertNotNull(resultado);
        assertEquals("Sede Central Café", resultado.getNombreCafeteria());
        verify(cafeteriaRepository, times(1)).save(cafeteriaExistente);
    }
}