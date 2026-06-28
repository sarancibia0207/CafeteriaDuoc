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

import com.example.cafeteriaduoc.msubicacion.DTO.RegionDTO;
import com.example.cafeteriaduoc.msubicacion.model.Region;
import com.example.cafeteriaduoc.msubicacion.repository.RegionRepository;
import com.example.cafeteriaduoc.msubicacion.service.RegionService;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
public class RegionServiceTest {

    @Mock
    private RegionRepository regionRepository;

    @InjectMocks
    private RegionService regionService;

    private final Faker faker = new Faker();

    private Region crearRegion() {
        Region region = new Region();
        region.setRegionId(faker.number().numberBetween(1, 16));
        // Usamos state() para simular nombres de regiones reales
        region.setNombreRegion(faker.address().state()); 
        return region;
    }

    @Test
    public void testObtenerTodos() {
        Region region = crearRegion();
        when(regionRepository.findAll()).thenReturn(List.of(region));

        List<RegionDTO> resultado = regionService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(region.getNombreRegion(), resultado.get(0).getNombreRegion());
    }

    @Test
    public void testBuscarPorId() {
        Region region = crearRegion();
        Integer id = region.getRegionId();
        when(regionRepository.findById(id)).thenReturn(Optional.of(region));

        RegionDTO resultado = regionService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getRegionId());
        assertEquals(region.getNombreRegion(), resultado.getNombreRegion());
        verify(regionRepository, times(1)).findById(id);
    }

    @Test
    public void testGuardar() {
        Region nuevaRegion = crearRegion();
        when(regionRepository.save(any(Region.class))).thenReturn(nuevaRegion);

        RegionDTO resultado = regionService.guardar(nuevaRegion);

        assertNotNull(resultado);
        assertEquals(nuevaRegion.getNombreRegion(), resultado.getNombreRegion());
        verify(regionRepository, times(1)).save(nuevaRegion);
    }
}