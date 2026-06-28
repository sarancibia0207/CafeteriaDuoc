package com.example.cafeteriaduoc.msventa.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.cafeteriaduoc.msventa.model.ProductoVenta;
import com.example.cafeteriaduoc.msventa.repository.ProductoventaRepository;
import com.example.cafeteriaduoc.msventa.service.ProductoVentaService;

@ExtendWith(MockitoExtension.class)
public class ProductoVentaServiceTest {

    @Mock
    private ProductoventaRepository productoventaRepository;

    @InjectMocks
    private ProductoVentaService productoVentaService;

    @Test
    public void testObtenerTodos() {
        ProductoVenta relacionMock = new ProductoVenta();
        
        when(productoventaRepository.findAll()).thenReturn(List.of(relacionMock));

        List<ProductoVenta> resultado = productoVentaService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(productoventaRepository, times(1)).findAll();
    }

    @Test
    public void testGuardar() {
        ProductoVenta relacionMock = new ProductoVenta();
        
        when(productoventaRepository.save(any(ProductoVenta.class))).thenReturn(relacionMock);

        ProductoVenta resultado = productoVentaService.guardar(relacionMock);

        assertNotNull(resultado);
        verify(productoventaRepository, times(1)).save(relacionMock);
    }
}