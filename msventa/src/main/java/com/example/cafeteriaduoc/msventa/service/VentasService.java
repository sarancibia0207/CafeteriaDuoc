package com.example.cafeteriaduoc.msventa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.cafeteriaduoc.msventa.DTO.CafeteriaDTO;
import com.example.cafeteriaduoc.msventa.DTO.ClienteDTO;
import com.example.cafeteriaduoc.msventa.DTO.ProductosDTO;
import com.example.cafeteriaduoc.msventa.DTO.VentasDTO;
import com.example.cafeteriaduoc.msventa.model.Metodospago;
import com.example.cafeteriaduoc.msventa.model.ProductoVenta;
import com.example.cafeteriaduoc.msventa.model.Ventas;
import com.example.cafeteriaduoc.msventa.repository.VentasRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class VentasService {
    @Autowired
    private VentasRepository ventasRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<VentasDTO> obtenerTodos(){
        log.info("Obteniendo todas las ventas");
        return ventasRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public VentasDTO buscarPorId(Integer id){
        log.info("Buscando venta con ID: {}", id);
        Ventas venta = ventasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La venta no existe!"));
        return convertirADTO(venta);
    }

    public VentasDTO guardar(Ventas nuevaVenta){
        log.info("Guardando nueva venta");
        Ventas ventaGuardada = ventasRepository.save(nuevaVenta);
        return convertirADTO(ventaGuardada);
    }

    private VentasDTO convertirADTO(Ventas venta){
        VentasDTO dto = new VentasDTO();
        dto.setVentaId(venta.getVentaId());
        dto.setFechaVenta(venta.getFechaVenta());

        // llamada a msubicacion
        try {
            CafeteriaDTO cafeteria = webClientBuilder.build()
                .get()
                .uri("http://msubicacion/api/v1/cafeterias/" + venta.getIdCafeteria())
                .retrieve()
                .bodyToMono(CafeteriaDTO.class)
                .block();
            if (cafeteria != null) dto.setNombreCafeteria(cafeteria.getNombreCafeteria());
        } catch (Exception e) {
            dto.setNombreCafeteria("Cafeteria no disponible.");
        }

        // llamada a mscliente
        try {
            ClienteDTO cliente = webClientBuilder.build()
                .get()
                .uri("http://mscliente/api/v1/clientes/" + venta.getIdCliente())
                .retrieve()
                .bodyToMono(ClienteDTO.class)
                .block();
            if (cliente != null) dto.setNombreCliente(cliente.getNombreCliente());
        } catch (Exception e) {
            dto.setNombreCliente("Cliente no disponible.");
        }

        // llamada a msproductos
        List<String> productos = new ArrayList<>();
        if (venta.getProductoVenta() != null) {
            for (ProductoVenta productoVenta : venta.getProductoVenta()) {
                try {
                    ProductosDTO producto = webClientBuilder.build()
                        .get()
                        .uri("http://msproductos/api/v1/productos/" + productoVenta.getIdProducto())
                        .retrieve()
                        .bodyToMono(ProductosDTO.class)
                        .block();
                    if (producto != null) productos.add(producto.getNombre());
                } catch (Exception e) {
                    productos.add("Producto no disponible.");
                }
            }
        }
        dto.setProductos(productos);

        List<String> metodoPago = new ArrayList<>();
        if (venta.getMetodosPago() != null) {
            for (Metodospago metodospago : venta.getMetodosPago()) {
                metodoPago.add(metodospago.getMetodopago().getTipoMetodoPago());
            }
        }
        dto.setMetodoPago(metodoPago);
        dto.setTotalVenta(venta.getTotalVenta());
        return dto;
    }
}
