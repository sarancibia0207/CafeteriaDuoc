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

import com.example.cafeteriaduoc.msproductos.DTO.TipoDTO;
import com.example.cafeteriaduoc.msproductos.assemblers.TipoModelAssembler;
import com.example.cafeteriaduoc.msproductos.model.Tipo;
import com.example.cafeteriaduoc.msproductos.service.TipoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tipos")
@Tag(name = "Tipos", description = "Operaciones relacionadas con los tipos de producto")
public class TipoController {
    @Autowired
    private TipoService tipoService;

    @Autowired
    private TipoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todos los tipos", description = "Obtiene una lista de todos los tipos de producto")
    public ResponseEntity<?> todosLostTipo() {
        List<EntityModel<TipoDTO>> lista = tipoService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(CollectionModel.of(lista,
                linkTo(methodOn(TipoController.class).todosLostTipo()).withSelfRel()));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener un tipo por su ID", description = "Obtiene el tipo por el ID ingresado")
    public ResponseEntity<?> tipoPorId(@PathVariable Integer id) {
        try {
            TipoDTO tipo = tipoService.buscarPorId(id);
            return ResponseEntity.ok(assembler.toModel(tipo));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se encontró el tipo.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Agregar un tipo", description = "Agrega un tipo de producto a la base de datos")
    public ResponseEntity<?> agregarTipo(@Valid @RequestBody Tipo tipo) {
        try {
            TipoDTO dto = tipoService.guardar(tipo);
            return ResponseEntity
                .created(linkTo(methodOn(TipoController.class).tipoPorId(dto.getTipoId())).toUri())
                .body(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se guardó el tipo.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar un tipo", description = "Elimina un tipo de producto de la base de datos")
    public ResponseEntity<?> eliminarTipo(@PathVariable Integer id) {
        try {
            String mensaje = tipoService.eliminar(id);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
