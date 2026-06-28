package com.example.cafeteriaduoc.msproductos.controller;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cafeteriaduoc.msproductos.DTO.IngredienteDTO;
import com.example.cafeteriaduoc.msproductos.assemblers.IngredienteModelAssembler;
import com.example.cafeteriaduoc.msproductos.model.Ingrediente;
import com.example.cafeteriaduoc.msproductos.service.IngredienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/ingredientes")
@Tag(name = "Ingredientes", description = "Operaciones relacionadas con los ingredientes")
public class IngredienteController {
    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private IngredienteModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todos los ingredientes", description = "Obtiene una lista de todos los ingredientes")
    public ResponseEntity<?> todosLosIngredientes() {
        List<EntityModel<IngredienteDTO>> lista = ingredienteService.obtenerTodas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(CollectionModel.of(lista,
                linkTo(methodOn(IngredienteController.class).todosLosIngredientes()).withSelfRel()));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener un ingrediente por su ID", description = "Obtiene el ingrediente por el ID ingresado")
    public ResponseEntity<?> ingredientePorId(@PathVariable Integer id) {
        try {
            IngredienteDTO ingrediente = ingredienteService.buscarPorId(id);
            return ResponseEntity.ok(assembler.toModel(ingrediente));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se encontró el ingrediente.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Agregar un ingrediente", description = "Agrega un ingrediente a la base de datos")
    public ResponseEntity<?> agregarIngrediente(@Valid @RequestBody Ingrediente ingrediente) {
        try {
            IngredienteDTO dto = ingredienteService.guardar(ingrediente);
            return ResponseEntity
                .created(linkTo(methodOn(IngredienteController.class).ingredientePorId(dto.getIngredienteId())).toUri())
                .body(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se guardó el ingrediente.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar un ingrediente", description = "Elimina un ingrediente de la base de datos")
    public ResponseEntity<?> eliminarIngrediente(@PathVariable Integer id) {
        try {
            String mensaje = ingredienteService.eliminar(id);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar un ingrediente completo", description = "Actualiza todos los campos del ingrediente")
    public ResponseEntity<?> actualizarIngrediente(@PathVariable Integer id, @Valid @RequestBody Ingrediente ingrediente) {
        try {
            IngredienteDTO dto = ingredienteService.actualizarIngrediente(id, ingrediente);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar un ingrediente parcialmente", description = "Actualiza solo los campos enviados del ingrediente")
    public ResponseEntity<?> patchIngrediente(@PathVariable Integer id, @RequestBody Ingrediente ingrediente) {
        try {
            IngredienteDTO dto = ingredienteService.patchIngrediente(id, ingrediente);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
