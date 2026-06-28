package com.example.cafeteriaduoc.msventa.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.cafeteriaduoc.msventa.DTO.MetodoPagoDTO;
import com.example.cafeteriaduoc.msventa.controller.MetodopagoController;

@Component
public class MetodoPagoModelAssembler implements RepresentationModelAssembler<MetodoPagoDTO, EntityModel<MetodoPagoDTO>> {
    @Override
    public EntityModel<MetodoPagoDTO> toModel(MetodoPagoDTO metodoPago) {
        return EntityModel.of(metodoPago,
            linkTo(methodOn(MetodopagoController.class).todosLosMetodoPago()).withRel("metodosPago"));
    }
}
