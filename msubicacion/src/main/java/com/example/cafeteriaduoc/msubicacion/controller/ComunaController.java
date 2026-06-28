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


import com.example.cafeteriaduoc.msubicacion.DTO.ComunaDTO;
import com.example.cafeteriaduoc.msubicacion.assemblers.ComunaModelAssembler;
import com.example.cafeteriaduoc.msubicacion.model.Comuna;
import com.example.cafeteriaduoc.msubicacion.service.ComunaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/comunas")
@Tag(name = "Comunas", description = "Operaciones relacionadas con las comunas")
public class ComunaController {
    @Autowired
    private ComunaService comunaService;

    @Autowired
    private ComunaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todas las comunas", description = "Obtiene una lista de todas las comunas")
    public ResponseEntity<?> todasLasComunas() {
        List<EntityModel<ComunaDTO>> lista = comunaService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(CollectionModel.of(lista,
                linkTo(methodOn(ComunaController.class).todasLasComunas()).withSelfRel()));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener una comuna por su ID", description = "Obtiene la comuna por el ID ingresado")
    public ResponseEntity<?> comunaPorId(@PathVariable Integer id){
        try {
            ComunaDTO comuna = comunaService.buscarPorId(id);
            return ResponseEntity.ok(assembler.toModel(comuna));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se encontró la comuna.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Agregar una comuna", description = "Agrega una comuna a la base de datos")
    public ResponseEntity<?> agregarComuna(@Valid @RequestBody Comuna comuna) {
        try {
            ComunaDTO dto = comunaService.guardar(comuna);
            return ResponseEntity
                .created(linkTo(methodOn(ComunaController.class).comunaPorId(dto.getComunaId())).toUri())
                .body(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se guardó la comuna.", HttpStatus.BAD_REQUEST);
        }
    }

}
