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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cafeteriaduoc.msproductos.DTO.TipoIngredienteDTO;
import com.example.cafeteriaduoc.msproductos.assemblers.TipoIngredienteModelAssembler;
import com.example.cafeteriaduoc.msproductos.model.TipoIngrediente;
import com.example.cafeteriaduoc.msproductos.service.TipoIngredienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tipoingredientes")
@Tag(name = "Tipos de Ingrediente", description = "Operaciones relacionadas con los tipos de ingrediente")
public class TipoIngredienteController {
    @Autowired
    private TipoIngredienteService tipoIngredienteService;

    @Autowired
    private TipoIngredienteModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todos los tipos de ingrediente", description = "Obtiene una lista de todos los tipos de ingrediente")
    public ResponseEntity<?> todosLosTipoIngrediente() {
        List<EntityModel<TipoIngredienteDTO>> lista = tipoIngredienteService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(CollectionModel.of(lista,
                linkTo(methodOn(TipoIngredienteController.class).todosLosTipoIngrediente()).withSelfRel()));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener un tipo de ingrediente por su ID", description = "Obtiene el tipo de ingrediente por el ID ingresado")
    public ResponseEntity<?> tipoIngredientePorId(@PathVariable Integer id) {
        try {
            TipoIngredienteDTO tipo = tipoIngredienteService.buscarPorId(id);
            return ResponseEntity.ok(assembler.toModel(tipo));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se encontró el tipo de ingrediente.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Agregar un tipo de ingrediente", description = "Agrega un tipo de ingrediente a la base de datos")
    public ResponseEntity<?> agregarTipoIngrediente(@Valid @RequestBody TipoIngrediente tipoIngrediente) {
        try {
            TipoIngredienteDTO dto = tipoIngredienteService.guardar(tipoIngrediente);
            return ResponseEntity
                .created(linkTo(methodOn(TipoIngredienteController.class).tipoIngredientePorId(dto.getTipoIngredienteId())).toUri())
                .body(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se pudo guardar el tipo de ingrediente.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar un tipo de ingrediente", description = "Elimina un tipo de ingrediente de la base de datos")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            String mensaje = tipoIngredienteService.eliminar(id);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}