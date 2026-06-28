package com.example.cafeteriaduoc.msproductos.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.cafeteriaduoc.msproductos.DTO.TipoDTO;
import com.example.cafeteriaduoc.msproductos.controller.TipoController;

@Component
public class TipoModelAssembler implements RepresentationModelAssembler<TipoDTO, EntityModel<TipoDTO>>{
    @Override
    public EntityModel<TipoDTO> toModel(TipoDTO tipo) {
        return EntityModel.of(tipo,
            linkTo(methodOn(TipoController.class).tipoPorId(tipo.getTipoId())).withSelfRel(),
            linkTo(methodOn(TipoController.class).todosLostTipo()).withRel("tipos"),
            linkTo(methodOn(TipoController.class).eliminarTipo(tipo.getTipoId())).withRel("eliminar"));
    }
}
