package com.example.cafeteriaduoc.msventa.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.example.cafeteriaduoc.msventa.DTO.MetodoPagoDTO;
import com.example.cafeteriaduoc.msventa.model.Metodopago;
import com.example.cafeteriaduoc.msventa.repository.MetodopagoRepository;
import com.example.cafeteriaduoc.msventa.service.MetodopagoService;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
public class MetodoPagoServiceTest {

    @Mock
    private MetodopagoRepository metodopagoRepository;

    @InjectMocks
    private MetodopagoService metodopagoService;

    private final Faker faker = new Faker();

    private Metodopago crearMetodoPago() {
        Metodopago metodoPago = new Metodopago();
        metodoPago.setMetodopagoId(faker.number().numberBetween(1, 10));
        metodoPago.setTipoMetodoPago(faker.options().option("Efectivo", "Tarjeta de Débito", "Tarjeta de Crédito", "Transferencia", "Junaeb")); 
        return metodoPago;
    }

    @Test
    public void testObtenerTodos() {
        Metodopago metodoPago = crearMetodoPago();
        when(metodopagoRepository.findAll()).thenReturn(List.of(metodoPago));

        List<MetodoPagoDTO> resultado = metodopagoService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(metodoPago.getTipoMetodoPago(), resultado.get(0).getTipoMetodoPago());
    }

    @Test
    public void testBuscarPorId() {
        Metodopago metodoPago = crearMetodoPago();
        Integer id = metodoPago.getMetodopagoId();
        when(metodopagoRepository.findById(id)).thenReturn(Optional.of(metodoPago));

        MetodoPagoDTO resultado = metodopagoService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getMetodoPagoId());
        assertEquals(metodoPago.getTipoMetodoPago(), resultado.getTipoMetodoPago());
        verify(metodopagoRepository, times(1)).findById(id);
    }
}