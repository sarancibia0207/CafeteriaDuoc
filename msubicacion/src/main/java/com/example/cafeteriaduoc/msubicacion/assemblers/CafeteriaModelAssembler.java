package com.example.cafeteriaduoc.msubicacion.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import org.springframework.stereotype.Component;

import com.example.cafeteriaduoc.msubicacion.DTO.CafeteriaDTO;
import com.example.cafeteriaduoc.msubicacion.controller.CafeteriaController;

@Component
public class CafeteriaModelAssembler implements RepresentationModelAssembler<CafeteriaDTO, EntityModel<CafeteriaDTO>>{
    @Override
    public EntityModel<CafeteriaDTO> toModel(CafeteriaDTO cafeteria){
        return EntityModel.of(cafeteria,
        linkTo(methodOn(CafeteriaController.class).cafeteriaPorId(cafeteria.getCafeteriaId())).withSelfRel(),
        linkTo(methodOn(CafeteriaController.class).todasLasCafeterias()).withRel("cafeterias"),
        linkTo(methodOn(CafeteriaController.class).eliminarCafeteria(cafeteria.getCafeteriaId())).withRel("eliminar"),
        linkTo(methodOn(CafeteriaController.class).actualizarCafeteria(cafeteria.getCafeteriaId(), null)).withRel("actualizar"),
        linkTo(methodOn(CafeteriaController.class).patchCafeteria(cafeteria.getCafeteriaId(), null)).withRel("actualizarParcial"));
    }
}
