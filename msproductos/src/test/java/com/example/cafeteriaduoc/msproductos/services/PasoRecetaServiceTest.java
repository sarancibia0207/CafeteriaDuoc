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

import com.example.cafeteriaduoc.msproductos.DTO.PasoRecetaDTO;
import com.example.cafeteriaduoc.msproductos.model.PasoReceta;
import com.example.cafeteriaduoc.msproductos.repository.PasorecetaRepository;
import com.example.cafeteriaduoc.msproductos.service.PasoRecetaService;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
public class PasoRecetaServiceTest {

    @Mock
    private PasorecetaRepository pasorecetaRepository;

    @InjectMocks
    private PasoRecetaService pasoRecetaService;

    private final Faker faker = new Faker();

    private PasoReceta crearPasoReceta() {
        PasoReceta pasoReceta = new PasoReceta();
        pasoReceta.setPasoRecetaId(faker.number().numberBetween(1, 100));
        pasoReceta.setTituloReceta(faker.food().dish()); // Título con nombre de plato de comida
        pasoReceta.setDescripcionPaso(faker.lorem().sentence()); // Descripción simulada
        return pasoReceta;
    }

    @Test
    public void testObtenerTodos() {
        PasoReceta pasoReceta = crearPasoReceta();
        when(pasorecetaRepository.findAll()).thenReturn(List.of(pasoReceta));

        List<PasoRecetaDTO> resultado = pasoRecetaService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(pasoReceta.getTituloReceta(), resultado.get(0).getTituloReceta());
    }

    @Test
    public void testBuscarPorId() {
        PasoReceta pasoReceta = crearPasoReceta();
        Integer id = pasoReceta.getPasoRecetaId();
        when(pasorecetaRepository.findById(id)).thenReturn(Optional.of(pasoReceta));

        PasoRecetaDTO resultado = pasoRecetaService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getPasoRecetaId());
        assertEquals(pasoReceta.getTituloReceta(), resultado.getTituloReceta());
        verify(pasorecetaRepository, times(1)).findById(id);
    }

    @Test
    public void testGuardar() {
        PasoReceta nuevaReceta = crearPasoReceta();
        when(pasorecetaRepository.save(any(PasoReceta.class))).thenReturn(nuevaReceta);

        PasoRecetaDTO resultado = pasoRecetaService.guardar(nuevaReceta);

        assertNotNull(resultado);
        assertEquals(nuevaReceta.getTituloReceta(), resultado.getTituloReceta());
        verify(pasorecetaRepository, times(1)).save(nuevaReceta);
    }

    @Test
    public void testEliminar() {
        PasoReceta pasoReceta = crearPasoReceta();
        Integer id = pasoReceta.getPasoRecetaId();
        
        when(pasorecetaRepository.findById(id)).thenReturn(Optional.of(pasoReceta));
        doNothing().when(pasorecetaRepository).delete(pasoReceta);

        String resultado = pasoRecetaService.eliminar(id);

        assertTrue(resultado.contains("ha sido eliminada"));
        verify(pasorecetaRepository, times(1)).delete(pasoReceta);
    }

    @Test
    public void testActualizarPasoReceta() {
        PasoReceta recetaExistente = crearPasoReceta();
        Integer id = recetaExistente.getPasoRecetaId();
        PasoReceta recetaNuevosDatos = crearPasoReceta(); 
        
        when(pasorecetaRepository.findById(id)).thenReturn(Optional.of(recetaExistente));
        when(pasorecetaRepository.save(any(PasoReceta.class))).thenReturn(recetaExistente);

        PasoRecetaDTO resultado = pasoRecetaService.actualizarPasoReceta(id, recetaNuevosDatos);

        assertNotNull(resultado);
        assertEquals(recetaNuevosDatos.getTituloReceta(), resultado.getTituloReceta());
        verify(pasorecetaRepository, times(1)).save(recetaExistente);
    }

    @Test
    public void testPatchPasoReceta() {
        PasoReceta recetaExistente = crearPasoReceta();
        Integer id = recetaExistente.getPasoRecetaId();
        
        PasoReceta recetaParcial = new PasoReceta();
        recetaParcial.setTituloReceta("Café Helado Parcheado");
        
        when(pasorecetaRepository.findById(id)).thenReturn(Optional.of(recetaExistente));
        when(pasorecetaRepository.save(any(PasoReceta.class))).thenReturn(recetaExistente);

        PasoRecetaDTO resultado = pasoRecetaService.patchPasoReceta(id, recetaParcial);

        assertNotNull(resultado);
        assertEquals("Café Helado Parcheado", resultado.getTituloReceta());
        verify(pasorecetaRepository, times(1)).save(recetaExistente);
    }
}