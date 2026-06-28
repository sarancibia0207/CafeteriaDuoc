package com.example.cafeteriaduoc.msubicacion.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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

import com.example.cafeteriaduoc.msubicacion.DTO.ComunaDTO;
import com.example.cafeteriaduoc.msubicacion.model.Comuna;
import com.example.cafeteriaduoc.msubicacion.model.Region;
import com.example.cafeteriaduoc.msubicacion.repository.ComunaRepository;
import com.example.cafeteriaduoc.msubicacion.service.ComunaService;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
public class ComunaServiceTest {

    @Mock
    private ComunaRepository comunaRepository;

    @InjectMocks
    private ComunaService comunaService;

    private final Faker faker = new Faker();

    private Comuna crearComuna() {
        Comuna comuna = new Comuna();
        comuna.setComunaId(faker.number().numberBetween(1, 300));
        comuna.setNombreComuna(faker.address().cityName());

        Region region = new Region();
        region.setRegionId(faker.number().numberBetween(1, 16));
        region.setNombreRegion(faker.address().state());
        comuna.setRegion(region);

        return comuna;
    }

    @Test
    public void testObtenerTodos() {
        Comuna comuna = crearComuna();
        when(comunaRepository.findAll()).thenReturn(List.of(comuna));

        List<ComunaDTO> resultado = comunaService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(comuna.getNombreComuna(), resultado.get(0).getNombreComuna());

        assertEquals(comuna.getRegion().getNombreRegion(), resultado.get(0).getNombreRegion());
    }

    @Test
    public void testBuscarPorId() {
        Comuna comuna = crearComuna();
        Integer id = comuna.getComunaId();
        when(comunaRepository.findById(id)).thenReturn(Optional.of(comuna));

        ComunaDTO resultado = comunaService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getComunaId());
        assertEquals(comuna.getNombreComuna(), resultado.getNombreComuna());
        verify(comunaRepository, times(1)).findById(id);
    }

    @Test
    public void testGuardar() {
        Comuna nuevaComuna = crearComuna();
        when(comunaRepository.save(any(Comuna.class))).thenReturn(nuevaComuna);

        ComunaDTO resultado = comunaService.guardar(nuevaComuna);

        assertNotNull(resultado);
        assertEquals(nuevaComuna.getNombreComuna(), resultado.getNombreComuna());
        verify(comunaRepository, times(1)).save(nuevaComuna);
    }
}