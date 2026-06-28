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

import com.example.cafeteriaduoc.msproductos.DTO.TamanoDTO;
import com.example.cafeteriaduoc.msproductos.assemblers.TamanoModelAssembler;
import com.example.cafeteriaduoc.msproductos.model.Tamano;
import com.example.cafeteriaduoc.msproductos.service.TamanoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tamanos")
@Tag(name = "Tamaños", description = "Operaciones relacionadas con los tamaños")
public class TamanoController {
    @Autowired
    private TamanoService tamanoService;

    @Autowired
    private TamanoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todos los tamaños", description = "Obtiene una lista de todos los tamaños")
    public ResponseEntity<?> todosLosTamanos() {
        List<EntityModel<TamanoDTO>> lista = tamanoService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(CollectionModel.of(lista,
                linkTo(methodOn(TamanoController.class).todosLosTamanos()).withSelfRel()));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener un tamaño por su ID", description = "Obtiene el tamaño por el ID ingresado")
    public ResponseEntity<?> tamanoPorId(@PathVariable Integer id) {
        try {
            TamanoDTO tamano = tamanoService.buscarPorId(id);
            return ResponseEntity.ok(assembler.toModel(tamano));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se encontró el tamaño.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Agregar un tamaño", description = "Agrega un tamaño a la base de datos")
    public ResponseEntity<?> agregarTamano(@Valid @RequestBody Tamano tamano) {
        try {
            TamanoDTO dto = tamanoService.guardar(tamano);
            return ResponseEntity
                .created(linkTo(methodOn(TamanoController.class).tamanoPorId(dto.getTamanoId())).toUri())
                .body(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se guardó el tamaño.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar un tamaño", description = "Elimina un tamaño de la base de datos")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            String mensaje = tamanoService.eliminar(id);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}