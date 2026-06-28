package com.example.cafeteriaduoc.msubicacion.controller;
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


import com.example.cafeteriaduoc.msubicacion.DTO.CafeteriaDTO;
import com.example.cafeteriaduoc.msubicacion.assemblers.CafeteriaModelAssembler;
import com.example.cafeteriaduoc.msubicacion.model.Cafeteria;
import com.example.cafeteriaduoc.msubicacion.service.CafeteriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/cafeterias")
@Tag(name = "Cafeterías", description = "Operaciones relacionadas con las cafeterías")
public class CafeteriaController {
    @Autowired
    private CafeteriaService cafeteriaService;

    @Autowired
    private CafeteriaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todas las cafeterías", description = "Obtiene una lista de todas las cafeterías")
    public ResponseEntity<?> todasLasCafeterias(){
        List<EntityModel<CafeteriaDTO>> cafeterias = cafeteriaService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if(cafeterias.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(CollectionModel.of(cafeterias,
                linkTo(methodOn(CafeteriaController.class).todasLasCafeterias()).withSelfRel()));
    }


    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener una cafetería por su ID", description = "Obtiene la cafetería por el ID ingresado")
    public ResponseEntity<?> cafeteriaPorId(@PathVariable Integer id){
        try {
            CafeteriaDTO cafeteria = cafeteriaService.buscarPorId(id);
            return ResponseEntity.ok(assembler.toModel(cafeteria));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se encontro la cafeteria", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Agregar una cafetería", description = "Agrega una cafetería a la base de datos")
    public ResponseEntity<?> agregarCafeteria(@Valid @RequestBody Cafeteria cafeteria){
        try {
            CafeteriaDTO cafeteriaDTO = cafeteriaService.guardar(cafeteria);
            return ResponseEntity
                .created(linkTo(methodOn(CafeteriaController.class).cafeteriaPorId(cafeteriaDTO.getCafeteriaId())).toUri())
                .body(assembler.toModel(cafeteriaDTO));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se guardó la cafeteria", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar una cafetería", description = "Elimina una cafetería de la base de datos")
    public ResponseEntity<?> eliminarCafeteria(@PathVariable Integer id){
        try {
            String mensaje = cafeteriaService.eliminar(id);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar una cafetería completa", description = "Actualiza todos los campos de la cafetería")
    public ResponseEntity<?> actualizarCafeteria(@PathVariable Integer id, @Valid @RequestBody Cafeteria cafeteria) {
        try {
            CafeteriaDTO dto = cafeteriaService.actualizarCafeteria(id, cafeteria);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar una cafetería parcialmente", description = "Actualiza solo los campos enviados de la cafetería")
    public ResponseEntity<?> patchCafeteria(@PathVariable Integer id, @RequestBody Cafeteria cafeteria) {
        try {
            CafeteriaDTO dto = cafeteriaService.patchCafeteria(id, cafeteria);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
