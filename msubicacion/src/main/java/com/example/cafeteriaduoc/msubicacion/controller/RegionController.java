package com.example.cafeteriaduoc.msubicacion.controller;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.cafeteriaduoc.msubicacion.DTO.RegionDTO;
import com.example.cafeteriaduoc.msubicacion.assemblers.RegionModelAssembler;
import com.example.cafeteriaduoc.msubicacion.model.Region;
import com.example.cafeteriaduoc.msubicacion.service.RegionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/regiones")
@Tag(name = "Regiones", description = "Operaciones relacionadas con las regiones")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @Autowired
    private RegionModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todas las regiones", description = "Obtiene una lista de todas las regiones")
    public ResponseEntity<?> todasLasRegiones() {
        List<EntityModel<RegionDTO>> lista = regionService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(CollectionModel.of(lista,
                linkTo(methodOn(RegionController.class).todasLasRegiones()).withSelfRel()));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener una región por su ID", description = "Obtiene la región por el ID ingresado")
    public ResponseEntity<?> regionPorId(@PathVariable Integer id){
        try {
            RegionDTO region = regionService.buscarPorId(id);
            return ResponseEntity.ok(assembler.toModel(region));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se encontró la región.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Agregar una región", description = "Agrega una región a la base de datos")
    public ResponseEntity<?> agregarRegion(@Valid @RequestBody Region region) {
        try {
            RegionDTO dto = regionService.guardar(region);
            return ResponseEntity
                .created(linkTo(methodOn(RegionController.class).regionPorId(dto.getRegionId())).toUri())
                .body(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se guardó la región.", HttpStatus.BAD_REQUEST);
        }
    }
}