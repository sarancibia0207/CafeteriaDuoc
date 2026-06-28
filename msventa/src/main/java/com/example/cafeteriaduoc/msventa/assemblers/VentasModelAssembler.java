package com.example.cafeteriaduoc.msventa.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.cafeteriaduoc.msventa.DTO.VentasDTO;
import com.example.cafeteriaduoc.msventa.controller.VentasController;

@Component
public class VentasModelAssembler implements RepresentationModelAssembler<VentasDTO, EntityModel<VentasDTO>>{
    @Override
    public EntityModel<VentasDTO> toModel(VentasDTO venta) {
        return EntityModel.of(venta,
            linkTo(methodOn(VentasController.class).ventasPorId(venta.getVentaId())).withSelfRel(),
            linkTo(methodOn(VentasController.class).todasLasVentas()).withRel("ventas"));
    }
}
