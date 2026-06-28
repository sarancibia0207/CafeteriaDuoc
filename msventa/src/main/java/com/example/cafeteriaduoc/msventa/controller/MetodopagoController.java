package com.example.cafeteriaduoc.msventa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


import com.example.cafeteriaduoc.msventa.DTO.MetodoPagoDTO;
import com.example.cafeteriaduoc.msventa.assemblers.MetodoPagoModelAssembler;
import com.example.cafeteriaduoc.msventa.service.MetodopagoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/metodospago")
@Tag(name = "Métodos de Pago", description = "Operaciones relacionadas con los métodos de pago")
public class MetodopagoController {
    @Autowired
    private MetodopagoService metodopagoService;

    @Autowired
    private MetodoPagoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todos los métodos de pago", description = "Obtiene una lista de todos los métodos de pago")
    public ResponseEntity<?> todosLosMetodoPago() {
        List<EntityModel<MetodoPagoDTO>> lista = metodopagoService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(CollectionModel.of(lista,
                linkTo(methodOn(MetodopagoController.class).todosLosMetodoPago()).withSelfRel()));
    }
}
