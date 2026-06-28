package com.example.cafeteriaduoc.mscliente.controller;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import com.example.cafeteriaduoc.mscliente.DTO.ClienteDTO;
import com.example.cafeteriaduoc.mscliente.assemblers.ClienteModelAssembler;
import com.example.cafeteriaduoc.mscliente.model.Cliente;
import com.example.cafeteriaduoc.mscliente.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Clientes", description = "Operaciones relacionadas con los clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todos los clientes", description = "Obtiene una lista de todos los clientes")
    public ResponseEntity<?> todosLosClientes() {
        List<EntityModel<ClienteDTO>> clientes = clienteService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (clientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(CollectionModel.of(clientes,
                linkTo(methodOn(ClienteController.class).todosLosClientes()).withSelfRel()));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener un cliente por su ID", description = "Obtiene el cliente por el ID ingresado")
    public ResponseEntity<?> clientePorId(@PathVariable Integer id) {
        try {
            ClienteDTO cliente = clienteService.buscarPorId(id);
            return ResponseEntity.ok(assembler.toModel(cliente));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se encontró el cliente.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/buscarNombre/{nombre}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar clientes por nombre", description = "Obtiene una lista de clientes que coincidan con el nombre ingresado")
    public ResponseEntity<?> clientePorNombre(@PathVariable String nombre) {
        try {
            List<EntityModel<ClienteDTO>> lista = clienteService.buscarPorNombre(nombre).stream()
                    .map(assembler::toModel)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(CollectionModel.of(lista,
                    linkTo(methodOn(ClienteController.class).clientePorNombre(nombre)).withSelfRel()));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se encontraron clientes.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Agregar un cliente", description = "Agrega un cliente a la base de datos")
    public ResponseEntity<?> agregarCliente(@Valid @RequestBody Cliente cliente) {
        try {
            ClienteDTO dto = clienteService.guardar(cliente);
            return ResponseEntity
                .created(linkTo(methodOn(ClienteController.class).clientePorId(dto.getClienteId())).toUri())
                .body(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se pudo guardar el cliente.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente de la base de datos")
    public ResponseEntity<?> eliminarCliente(@PathVariable Integer id) {
        try {
            String mensaje = clienteService.eliminar(id);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se pudo eliminar el cliente.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar un cliente completo", description = "Actualiza todos los campos del cliente")
    public ResponseEntity<?> actualizarCliente(@PathVariable Integer id, @Valid @RequestBody Cliente cliente) {
        try {
            ClienteDTO dto = clienteService.actualizar(id, cliente);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar un cliente parcialmente", description = "Actualiza solo los campos enviados del cliente")
    public ResponseEntity<?> patchCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        try {
            ClienteDTO dto = clienteService.patchCliente(id, cliente);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
