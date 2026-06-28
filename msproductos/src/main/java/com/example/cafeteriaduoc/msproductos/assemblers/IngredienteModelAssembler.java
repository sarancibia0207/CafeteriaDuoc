package com.example.cafeteriaduoc.msproductos.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.cafeteriaduoc.msproductos.DTO.IngredienteDTO;
import com.example.cafeteriaduoc.msproductos.controller.IngredienteController;

@Component
public class IngredienteModelAssembler implements RepresentationModelAssembler<IngredienteDTO, EntityModel<IngredienteDTO>>{
    @Override
    public EntityModel<IngredienteDTO> toModel(IngredienteDTO ingrediente) {
        return EntityModel.of(ingrediente,
            linkTo(methodOn(IngredienteController.class).ingredientePorId(ingrediente.getIngredienteId())).withSelfRel(),
            linkTo(methodOn(IngredienteController.class).todosLosIngredientes()).withRel("ingredientes"),
            linkTo(methodOn(IngredienteController.class).eliminarIngrediente(ingrediente.getIngredienteId())).withRel("eliminar"),
            linkTo(methodOn(IngredienteController.class).actualizarIngrediente(ingrediente.getIngredienteId(), null)).withRel("actualizar"),
            linkTo(methodOn(IngredienteController.class).patchIngrediente(ingrediente.getIngredienteId(), null)).withRel("patch"));
    }
}