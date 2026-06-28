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

import com.example.cafeteriaduoc.msproductos.DTO.TipoDTO;
import com.example.cafeteriaduoc.msproductos.model.Tipo;
import com.example.cafeteriaduoc.msproductos.repository.TipoRepository;
import com.example.cafeteriaduoc.msproductos.service.TipoService;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
public class TipoServiceTest {

    @Mock
    private TipoRepository tipoRepository;

    @InjectMocks
    private TipoService tipoService;

    private final Faker faker = new Faker();

    private Tipo crearTipo() {
        Tipo tipo = new Tipo();
        tipo.setTipoId(faker.number().numberBetween(1, 20));
        tipo.setNombreTipo(faker.options().option("Bebida Caliente", "Bebida Fría", "Pastelería", "Sándwich", "Merchandising"));
        return tipo;
    }

    @Test
    public void testObtenerTodos() {
        Tipo tipo = crearTipo();
        when(tipoRepository.findAll()).thenReturn(List.of(tipo));

        List<TipoDTO> resultado = tipoService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(tipo.getNombreTipo(), resultado.get(0).getNombreTipo());
    }

    @Test
    public void testBuscarPorId() {
        Tipo tipo = crearTipo();
        Integer id = tipo.getTipoId();
        when(tipoRepository.findById(id)).thenReturn(Optional.of(tipo));

        TipoDTO resultado = tipoService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getTipoId());
        assertEquals(tipo.getNombreTipo(), resultado.getNombreTipo());
        verify(tipoRepository, times(1)).findById(id);
    }

    @Test
    public void testGuardar() {
        Tipo nuevoTipo = crearTipo();
        when(tipoRepository.save(any(Tipo.class))).thenReturn(nuevoTipo);

        TipoDTO resultado = tipoService.guardar(nuevoTipo);

        assertNotNull(resultado);
        assertEquals(nuevoTipo.getNombreTipo(), resultado.getNombreTipo());
        verify(tipoRepository, times(1)).save(nuevoTipo);
    }

    @Test
    public void testEliminar() {
        Tipo tipo = crearTipo();
        Integer id = tipo.getTipoId();
        
        when(tipoRepository.findById(id)).thenReturn(Optional.of(tipo));
        doNothing().when(tipoRepository).delete(tipo);

        String resultado = tipoService.eliminar(id);

        assertTrue(resultado.contains("ha sido eliminado"));
        verify(tipoRepository, times(1)).delete(tipo);
    }
}