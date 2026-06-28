package com.example.cafeteriaduoc.msventa.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.cafeteriaduoc.msventa.DTO.CafeteriaDTO;
import com.example.cafeteriaduoc.msventa.DTO.ClienteDTO;
import com.example.cafeteriaduoc.msventa.DTO.ProductosDTO;
import com.example.cafeteriaduoc.msventa.DTO.VentasDTO;
import com.example.cafeteriaduoc.msventa.model.Metodopago;
import com.example.cafeteriaduoc.msventa.model.Metodospago;
import com.example.cafeteriaduoc.msventa.model.ProductoVenta;
import com.example.cafeteriaduoc.msventa.model.Ventas;
import com.example.cafeteriaduoc.msventa.repository.VentasRepository;
import com.example.cafeteriaduoc.msventa.service.VentasService;

import net.datafaker.Faker;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class VentasServiceTest {

    @Mock private VentasRepository ventasRepository;
    @Mock private WebClient.Builder webClientBuilder;
    @Mock private WebClient webClient;
    @SuppressWarnings("rawtypes")
    @Mock private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @SuppressWarnings("rawtypes")
    @Mock private WebClient.RequestHeadersSpec requestHeadersSpec;
    @Mock private WebClient.ResponseSpec responseSpec;

    @InjectMocks 
    private VentasService ventasService;
    private final Faker faker = new Faker();
    private CafeteriaDTO cafeteriaMock;
    private ClienteDTO clienteMock;
    private ProductosDTO productoMock;

    @BeforeEach
    @SuppressWarnings({"unchecked"})
    void setUpWebClientMock() {
        cafeteriaMock = new CafeteriaDTO();
        cafeteriaMock.setNombreCafeteria("Cafetería Central (Mock)");

        clienteMock = new ClienteDTO();
        clienteMock.setNombreCliente("Juan Pérez (Mock)");

        productoMock = new ProductosDTO();
        productoMock.setNombre("Café Latte (Mock)");

        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);

        when(responseSpec.bodyToMono(CafeteriaDTO.class)).thenReturn(Mono.just(cafeteriaMock));
        when(responseSpec.bodyToMono(ClienteDTO.class)).thenReturn(Mono.just(clienteMock));
        when(responseSpec.bodyToMono(ProductosDTO.class)).thenReturn(Mono.just(productoMock));
    }

    private Ventas crearVenta() {
        Ventas venta = new Ventas();
        venta.setVentaId(faker.number().numberBetween(1, 100));
        venta.setIdCafeteria(1);
        venta.setIdCliente(1);
        venta.setTotalVenta(Double.valueOf(faker.number().numberBetween(1500, 15000)));

        Metodopago mp = new Metodopago();
        mp.setTipoMetodoPago("Tarjeta de Crédito");
        Metodospago msp = new Metodospago();
        msp.setMetodopago(mp);
        venta.setMetodosPago(List.of(msp));

        ProductoVenta pv = new ProductoVenta();
        pv.setIdProducto(1);
        venta.setProductoVenta(List.of(pv));

        return venta;
    }

    @Test
    public void testObtenerTodos() {
        Ventas venta = crearVenta();
        when(ventasRepository.findAll()).thenReturn(List.of(venta));

        List<VentasDTO> resultado = ventasService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());

        assertEquals(cafeteriaMock.getNombreCafeteria(), resultado.get(0).getNombreCafeteria());
        assertEquals(clienteMock.getNombreCliente(), resultado.get(0).getNombreCliente());
        assertEquals(productoMock.getNombre(), resultado.get(0).getProductos().get(0));
    }

    @Test
    public void testBuscarPorId() {
        Ventas venta = crearVenta();
        Integer id = venta.getVentaId();
        when(ventasRepository.findById(id)).thenReturn(Optional.of(venta));

        VentasDTO resultado = ventasService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getVentaId());
        assertEquals(venta.getTotalVenta(), resultado.getTotalVenta());

        assertEquals("Tarjeta de Crédito", resultado.getMetodoPago().get(0));
        verify(ventasRepository, times(1)).findById(id);
    }

    @Test
    public void testGuardar() {
        Ventas nuevaVenta = crearVenta();
        when(ventasRepository.save(any(Ventas.class))).thenReturn(nuevaVenta);

        VentasDTO resultado = ventasService.guardar(nuevaVenta);

        assertNotNull(resultado);
        assertEquals(nuevaVenta.getTotalVenta(), resultado.getTotalVenta());
        verify(ventasRepository, times(1)).save(nuevaVenta);
    }
}