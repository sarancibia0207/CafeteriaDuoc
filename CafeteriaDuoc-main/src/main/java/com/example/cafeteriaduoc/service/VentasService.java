package com.example.cafeteriaduoc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafeteriaduoc.DTO.VentasDTO;
import com.example.cafeteriaduoc.model.Metodospago;
import com.example.cafeteriaduoc.model.ProductoVenta;
import com.example.cafeteriaduoc.model.Ventas;
import com.example.cafeteriaduoc.repository.VentasRepository;

@Service
@Transactional
public class VentasService {
    @Autowired
    private VentasRepository ventasRepository;

    public List<VentasDTO> obtenerTodos(){
        return ventasRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public VentasDTO buscarPorId(Integer id){
        Ventas venta = ventasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La venta no existe!"));
        return convertirADTO(venta);
    }

    public VentasDTO guardar(Ventas nuevaVenta){
        Ventas ventaGuardada = ventasRepository.save(nuevaVenta);
        return convertirADTO(ventaGuardada);
    }

    // Borrar?

    // Actualizar??

    private VentasDTO convertirADTO(Ventas venta){
        VentasDTO dto = new VentasDTO();
        dto.setVentaId(venta.getVentaId());
        if(venta.getCafeteria() != null){
            dto.setNombreCafeteria(venta.getCafeteria().getNombreCafeteria());
        }else{
            dto.setNombreCafeteria("Cafetería no indicada...");
        }
        dto.setFechaVenta(venta.getFechaVenta());
        if(venta.getCliente() != null){
            dto.setNombreCliente(venta.getCliente().getNombreCliente());
        }else{
            dto.setNombreCliente("Nombre del cliente no indicado...");
        }
        List<String> productos = new ArrayList<>();
        if(venta.getProductoVenta() != null){
            for (ProductoVenta productoVenta : venta.getProductoVenta()) {
                productos.add(productoVenta.getProducto().getNombre());
                
            }
        }
        dto.setProductos(productos);
        List<String> metodoPago = new ArrayList<>();
        if(venta.getMetodosPago() != null){
            for (Metodospago metodospago: venta.getMetodosPago()) {
                metodoPago.add(metodospago.getMetodopago().getTipoMetodoPago());
            }
        }
        dto.setMetodoPago(metodoPago);
        dto.setTotalVenta(venta.getTotalVenta());
        return dto;
    }
}
