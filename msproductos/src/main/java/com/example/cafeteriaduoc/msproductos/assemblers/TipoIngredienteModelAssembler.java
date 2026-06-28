package com.example.cafeteriaduoc.msproductos.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.cafeteriaduoc.msproductos.DTO.TipoIngredienteDTO;
import com.example.cafeteriaduoc.msproductos.controller.TipoIngredienteController;

@Component
public class TipoIngredienteModelAssembler implements RepresentationModelAssembler<TipoIngredienteDTO, EntityModel<TipoIngredienteDTO>>{
    @Override
    public EntityModel<TipoIngredienteDTO> toModel(TipoIngredienteDTO tipoIngrediente) {
        return EntityModel.of(tipoIngrediente,
            linkTo(methodOn(TipoIngredienteController.class).tipoIngredientePorId(tipoIngrediente.getTipoIngredienteId())).withSelfRel(),
            linkTo(methodOn(TipoIngredienteController.class).todosLosTipoIngrediente()).withRel("tipoIngredientes"),
            linkTo(methodOn(TipoIngredienteController.class).eliminar(tipoIngrediente.getTipoIngredienteId())).withRel("eliminar"));
    }
}
