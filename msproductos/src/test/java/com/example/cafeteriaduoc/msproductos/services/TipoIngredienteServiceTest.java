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

import com.example.cafeteriaduoc.msproductos.DTO.TipoIngredienteDTO;
import com.example.cafeteriaduoc.msproductos.model.TipoIngrediente;
import com.example.cafeteriaduoc.msproductos.repository.TipoIngredienteRepository;
import com.example.cafeteriaduoc.msproductos.service.TipoIngredienteService;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
public class TipoIngredienteServiceTest {

    @Mock
    private TipoIngredienteRepository tipoIngredienteRepository;

    @InjectMocks
    private TipoIngredienteService tipoIngredienteService;

    private final Faker faker = new Faker();

    private TipoIngrediente crearTipoIngrediente() {
        TipoIngrediente tipo = new TipoIngrediente();
        tipo.setTipoIngredienteId(faker.number().numberBetween(1, 50));
        tipo.setNombreTipoIngrediente(faker.options().option("Lácteo", "Endulzante", "Sirope", "Especia", "Topping"));
        return tipo;
    }

    @Test
    public void testObtenerTodos() {
        TipoIngrediente tipo = crearTipoIngrediente();
        when(tipoIngredienteRepository.findAll()).thenReturn(List.of(tipo));

        List<TipoIngredienteDTO> resultado = tipoIngredienteService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(tipo.getNombreTipoIngrediente(), resultado.get(0).getNombreTipoIngrediente());
    }

    @Test
    public void testBuscarPorId() {
        TipoIngrediente tipo = crearTipoIngrediente();
        Integer id = tipo.getTipoIngredienteId();
        when(tipoIngredienteRepository.findById(id)).thenReturn(Optional.of(tipo));

        TipoIngredienteDTO resultado = tipoIngredienteService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getTipoIngredienteId());
        assertEquals(tipo.getNombreTipoIngrediente(), resultado.getNombreTipoIngrediente());
        verify(tipoIngredienteRepository, times(1)).findById(id);
    }

    @Test
    public void testGuardar() {
        TipoIngrediente nuevoTipo = crearTipoIngrediente();
        when(tipoIngredienteRepository.save(any(TipoIngrediente.class))).thenReturn(nuevoTipo);

        TipoIngredienteDTO resultado = tipoIngredienteService.guardar(nuevoTipo);

        assertNotNull(resultado);
        assertEquals(nuevoTipo.getNombreTipoIngrediente(), resultado.getNombreTipoIngrediente());
        verify(tipoIngredienteRepository, times(1)).save(nuevoTipo);
    }

    @Test
    public void testEliminar() {
        TipoIngrediente tipo = crearTipoIngrediente();
        Integer id = tipo.getTipoIngredienteId();
        
        when(tipoIngredienteRepository.findById(id)).thenReturn(Optional.of(tipo));
        doNothing().when(tipoIngredienteRepository).delete(tipo);

        String resultado = tipoIngredienteService.eliminar(id);

        assertTrue(resultado.contains("ha sido eliminado"));
        verify(tipoIngredienteRepository, times(1)).delete(tipo);
    }
}