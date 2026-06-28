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

import com.example.cafeteriaduoc.msproductos.DTO.ProductosDTO;
import com.example.cafeteriaduoc.msproductos.model.Producto;
import com.example.cafeteriaduoc.msproductos.repository.ProductosRepository;
import com.example.cafeteriaduoc.msproductos.service.ProductosService;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
public class ProductosServiceTest {

    @Mock
    private ProductosRepository productosRepository;

    @InjectMocks
    private ProductosService productosService;

    private final Faker faker = new Faker();

    private Producto crearProducto() {
        Producto producto = new Producto();
        producto.setProductoId(faker.number().numberBetween(1, 100));
        producto.setNombre(faker.coffee().blendName()); // Nombres con temática de café
        producto.setPrecio(faker.number().numberBetween(1000, 5000)); // Precio entre 1000 y 5000
        producto.setStock(faker.number().numberBetween(10, 100));
        return producto;
    }

    @Test
    public void testObtenerTodos() {
        Producto producto = crearProducto();
        when(productosRepository.findAll()).thenReturn(List.of(producto));

        List<ProductosDTO> resultado = productosService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(producto.getNombre(), resultado.get(0).getNombre());
    }

    @Test
    public void testBuscarPorId() {
        Producto producto = crearProducto();
        Integer id = producto.getProductoId();
        when(productosRepository.findById(id)).thenReturn(Optional.of(producto));

        ProductosDTO resultado = productosService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getProductoId());
        assertEquals(producto.getNombre(), resultado.getNombre());
        verify(productosRepository, times(1)).findById(id);
    }

    @Test
    public void testGuardarProductos() {
        Producto nuevoProducto = crearProducto();
        when(productosRepository.save(any(Producto.class))).thenReturn(nuevoProducto);

        ProductosDTO resultado = productosService.guardarProductos(nuevoProducto);

        assertNotNull(resultado);
        assertEquals(nuevoProducto.getNombre(), resultado.getNombre());
        verify(productosRepository, times(1)).save(nuevoProducto);
    }

    @Test
    public void testActualizarProducto() {
        Producto productoExistente = crearProducto();
        Integer id = productoExistente.getProductoId();
        Producto productoNuevosDatos = crearProducto(); 
        
        when(productosRepository.findById(id)).thenReturn(Optional.of(productoExistente));
        when(productosRepository.save(any(Producto.class))).thenReturn(productoExistente);

        ProductosDTO resultado = productosService.actualizarProducto(id, productoNuevosDatos);

        assertNotNull(resultado);
        assertEquals(productoNuevosDatos.getNombre(), resultado.getNombre());
        verify(productosRepository, times(1)).save(productoExistente);
    }

    @Test
    public void testPatchProducto() {
        Producto productoExistente = crearProducto();
        Integer id = productoExistente.getProductoId();
        
        Producto productoParcial = new Producto();
        productoParcial.setNombre("Café Moca Parcheado");
        productoParcial.setStock(50);
        
        when(productosRepository.findById(id)).thenReturn(Optional.of(productoExistente));
        when(productosRepository.save(any(Producto.class))).thenReturn(productoExistente);

        ProductosDTO resultado = productosService.patchProducto(id, productoParcial);

        assertNotNull(resultado);
        assertEquals("Café Moca Parcheado", resultado.getNombre());
        verify(productosRepository, times(1)).save(productoExistente);
    }

    @Test
    public void testEliminarProducto() {
        Producto producto = crearProducto();
        Integer id = producto.getProductoId();
        
        when(productosRepository.findById(id)).thenReturn(Optional.of(producto));
        doNothing().when(productosRepository).delete(producto);

        String resultado = productosService.eliminarProducto(id);

        assertTrue(resultado.contains("ha sido eliminado"));
        verify(productosRepository, times(1)).delete(producto);
    }

    @Test
    public void testBuscarPorNombre() {
        Producto producto = crearProducto();
        String nombre = producto.getNombre();
        when(productosRepository.findByNombre(nombre)).thenReturn(List.of(producto));

        List<ProductosDTO> resultado = productosService.buscarPorNombre(nombre);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(nombre, resultado.get(0).getNombre());
    }

    @Test
    public void testBuscarPorPrecio() {
        Producto producto = crearProducto();
        Integer precio = producto.getPrecio();
        when(productosRepository.findByPrecio(precio)).thenReturn(List.of(producto));
        List<ProductosDTO> resultado = productosService.buscarPorPrecio(precio);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(precio, resultado.get(0).getPrecio());
    }

    @Test
    public void testBuscarStock() {
        Producto producto = crearProducto();
        Integer stockMinimo = 20; // Simulamos buscar un stock mínimo
        when(productosRepository.findByStock(stockMinimo)).thenReturn(List.of(producto));

        List<ProductosDTO> resultado = productosService.buscarStock(stockMinimo);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertNotNull(resultado.get(0)); 
    }
}