package com.example.cafeteriaduoc.msventa.controller;
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

import com.example.cafeteriaduoc.msventa.DTO.VentasDTO;
import com.example.cafeteriaduoc.msventa.assemblers.VentasModelAssembler;
import com.example.cafeteriaduoc.msventa.model.Ventas;
import com.example.cafeteriaduoc.msventa.service.VentasService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/ventas")
@Tag(name = "Ventas", description = "Operaciones relacionadas con las ventas")
public class VentasController {
    @Autowired
    private VentasService ventasService;

    @Autowired
    private VentasModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todas las ventas", description = "Obtiene una lista de todas las ventas")
    public ResponseEntity<?> todasLasVentas() {
        List<EntityModel<VentasDTO>> lista = ventasService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(CollectionModel.of(lista,
                linkTo(methodOn(VentasController.class).todasLasVentas()).withSelfRel()));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener una venta por su ID", description = "Obtiene la venta por el ID ingresado")
    public ResponseEntity<?> ventasPorId(@PathVariable Integer id) {
        try {
            VentasDTO venta = ventasService.buscarPorId(id);
            return ResponseEntity.ok(assembler.toModel(venta));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se encontró la venta.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Agregar una venta", description = "Agrega una venta a la base de datos")
    public ResponseEntity<?> agregarVenta(@Valid @RequestBody Ventas venta) {
        try {
            VentasDTO dto = ventasService.guardar(venta);
            return ResponseEntity
                .created(linkTo(methodOn(VentasController.class).ventasPorId(dto.getVentaId())).toUri())
                .body(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se guardó la venta.", HttpStatus.BAD_REQUEST);
        }
    }
}