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

import com.example.cafeteriaduoc.msproductos.DTO.PasoRecetaDTO;
import com.example.cafeteriaduoc.msproductos.assemblers.PasoRecetaModelAssembler;
import com.example.cafeteriaduoc.msproductos.model.PasoReceta;
import com.example.cafeteriaduoc.msproductos.service.PasoRecetaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pasosrecetas")
@Tag(name = "Pasos Receta", description = "Operaciones relacionadas con los pasos de receta")
public class PasoRecetaController {
    @Autowired
    private PasoRecetaService pasoRecetaService;

    @Autowired
    private PasoRecetaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todos los pasos de receta", description = "Obtiene una lista de todos los pasos de receta")
    public ResponseEntity<?> todosLosPasosReceta() {
        List<EntityModel<PasoRecetaDTO>> lista = pasoRecetaService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(CollectionModel.of(lista,
                linkTo(methodOn(PasoRecetaController.class).todosLosPasosReceta()).withSelfRel()));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener un paso de receta por su ID", description = "Obtiene el paso de receta por el ID ingresado")
    public ResponseEntity<?> pasoRecetaPorId(@PathVariable Integer id) {
        try {
            PasoRecetaDTO pasoReceta = pasoRecetaService.buscarPorId(id);
            return ResponseEntity.ok(assembler.toModel(pasoReceta));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se encontró la receta.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Agregar un paso de receta", description = "Agrega un paso de receta a la base de datos")
    public ResponseEntity<?> agregarPasoReceta(@Valid @RequestBody PasoReceta pasoReceta) {
        try {
            PasoRecetaDTO dto = pasoRecetaService.guardar(pasoReceta);
            return ResponseEntity
                .created(linkTo(methodOn(PasoRecetaController.class).pasoRecetaPorId(dto.getPasoRecetaId())).toUri())
                .body(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se guardó la receta.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar un paso de receta", description = "Elimina un paso de receta de la base de datos")
    public ResponseEntity<?> eliminarPasoReceta(@PathVariable Integer id) {
        try {
            String mensaje = pasoRecetaService.eliminar(id);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar un paso de receta completo", description = "Actualiza todos los campos del paso de receta")
    public ResponseEntity<?> actualizarPasoReceta(@PathVariable Integer id, @Valid @RequestBody PasoReceta pasoReceta) {
        try {
            PasoRecetaDTO dto = pasoRecetaService.actualizarPasoReceta(id, pasoReceta);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar un paso de receta parcialmente", description = "Actualiza solo los campos enviados del paso de receta")
    public ResponseEntity<?> patchPasoReceta(@PathVariable Integer id, @RequestBody PasoReceta pasoReceta) {
        try {
            PasoRecetaDTO dto = pasoRecetaService.patchPasoReceta(id, pasoReceta);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
