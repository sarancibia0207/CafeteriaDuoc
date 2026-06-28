package com.example.cafeteriaduoc.msubicacion.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.cafeteriaduoc.msubicacion.DTO.RegionDTO;
import com.example.cafeteriaduoc.msubicacion.controller.RegionController;

@Component
public class RegionModelAssembler implements RepresentationModelAssembler<RegionDTO, EntityModel<RegionDTO>>{
    @Override
    public EntityModel<RegionDTO> toModel(RegionDTO region) {
        return EntityModel.of(region,
            linkTo(methodOn(RegionController.class).regionPorId(region.getRegionId())).withSelfRel(),
            linkTo(methodOn(RegionController.class).todasLasRegiones()).withRel("regiones"));
    }
}
