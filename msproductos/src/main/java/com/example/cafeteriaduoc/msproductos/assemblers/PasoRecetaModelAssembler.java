package com.example.cafeteriaduoc.msproductos.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.cafeteriaduoc.msproductos.DTO.PasoRecetaDTO;
import com.example.cafeteriaduoc.msproductos.controller.PasoRecetaController;

@Component
public class PasoRecetaModelAssembler implements RepresentationModelAssembler<PasoRecetaDTO, EntityModel<PasoRecetaDTO>>{
    @Override
    public EntityModel<PasoRecetaDTO> toModel(PasoRecetaDTO pasoReceta) {
        return EntityModel.of(pasoReceta,
            linkTo(methodOn(PasoRecetaController.class).pasoRecetaPorId(pasoReceta.getPasoRecetaId())).withSelfRel(),
            linkTo(methodOn(PasoRecetaController.class).todosLosPasosReceta()).withRel("pasosReceta"),
            linkTo(methodOn(PasoRecetaController.class).eliminarPasoReceta(pasoReceta.getPasoRecetaId())).withRel("eliminar"),
            linkTo(methodOn(PasoRecetaController.class).actualizarPasoReceta(pasoReceta.getPasoRecetaId(), null)).withRel("actualizar"),
            linkTo(methodOn(PasoRecetaController.class).patchPasoReceta(pasoReceta.getPasoRecetaId(), null)).withRel("patch"));
    }
}
