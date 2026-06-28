package com.example.cafeteriaduoc.msubicacion.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import org.springframework.stereotype.Component;

import com.example.cafeteriaduoc.msubicacion.DTO.ComunaDTO;
import com.example.cafeteriaduoc.msubicacion.controller.ComunaController;

@Component
public class ComunaModelAssembler implements RepresentationModelAssembler<ComunaDTO, EntityModel<ComunaDTO>>{
    @Override
    public EntityModel<ComunaDTO> toModel(ComunaDTO comuna) {
        return EntityModel.of(comuna,
            linkTo(methodOn(ComunaController.class).comunaPorId(comuna.getComunaId())).withSelfRel(),
            linkTo(methodOn(ComunaController.class).todasLasComunas()).withRel("comunas"));
    }
}
