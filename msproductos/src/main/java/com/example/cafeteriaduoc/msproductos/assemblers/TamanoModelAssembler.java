package com.example.cafeteriaduoc.msproductos.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.cafeteriaduoc.msproductos.DTO.TamanoDTO;
import com.example.cafeteriaduoc.msproductos.controller.TamanoController;

@Component
public class TamanoModelAssembler implements RepresentationModelAssembler<TamanoDTO, EntityModel<TamanoDTO>>{
    @Override
    public EntityModel<TamanoDTO> toModel(TamanoDTO tamano) {
        return EntityModel.of(tamano,
            linkTo(methodOn(TamanoController.class).tamanoPorId(tamano.getTamanoId())).withSelfRel(),
            linkTo(methodOn(TamanoController.class).todosLosTamanos()).withRel("tamanos"),
            linkTo(methodOn(TamanoController.class).eliminar(tamano.getTamanoId())).withRel("eliminar"));
    }
}
