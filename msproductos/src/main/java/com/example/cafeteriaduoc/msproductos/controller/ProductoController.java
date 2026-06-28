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

import com.example.cafeteriaduoc.msproductos.DTO.ProductosDTO;
import com.example.cafeteriaduoc.msproductos.assemblers.ProductoModelAssembler;
import com.example.cafeteriaduoc.msproductos.model.Producto;
import com.example.cafeteriaduoc.msproductos.service.ProductosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos", description = "Operaciones relacionadas con los productos")
public class ProductoController {
    @Autowired
    private ProductosService productosService;

    @Autowired
    private ProductoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todos los productos", description = "Obtiene una lista de todos los productos")
    public ResponseEntity<?> todosLosProductos() {
        List<EntityModel<ProductosDTO>> lista = productosService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(CollectionModel.of(lista,
                linkTo(methodOn(ProductoController.class).todosLosProductos()).withSelfRel()));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener un producto por su ID", description = "Obtiene el producto por el ID ingresado")
    public ResponseEntity<?> productoPorId(@PathVariable Integer id) {
        try {
            ProductosDTO producto = productosService.buscarPorId(id);
            return ResponseEntity.ok(assembler.toModel(producto));
        } catch (Exception e) {
            return new ResponseEntity<>("No se encontró el producto.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/nombre/{nombre}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar productos por nombre", description = "Obtiene una lista de productos que coincidan con el nombre ingresado")
    public ResponseEntity<?> productoPorNombre(@PathVariable String nombre) {
        try {
            List<EntityModel<ProductosDTO>> lista = productosService.buscarPorNombre(nombre).stream()
                    .map(assembler::toModel)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(CollectionModel.of(lista,
                    linkTo(methodOn(ProductoController.class).productoPorNombre(nombre)).withSelfRel()));
        } catch (Exception e) {
            return new ResponseEntity<>("No se encontraron productos.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/stock/{stockMinimo}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar productos por stock mínimo", description = "Obtiene una lista de productos con al menos el stock ingresado")
    public ResponseEntity<?> productoPorStock(@PathVariable Integer stockMinimo) {
        try {
            List<EntityModel<ProductosDTO>> lista = productosService.buscarStock(stockMinimo).stream()
                    .map(assembler::toModel)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(CollectionModel.of(lista,
                    linkTo(methodOn(ProductoController.class).productoPorStock(stockMinimo)).withSelfRel()));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se encontraron productos.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/precio/{precio}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar productos por precio", description = "Obtiene una lista de productos con el precio ingresado")
    public ResponseEntity<?> productoPorPrecio(@PathVariable Integer precio) {
        try {
            List<EntityModel<ProductosDTO>> lista = productosService.buscarPorPrecio(precio).stream()
                    .map(assembler::toModel)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(CollectionModel.of(lista,
                    linkTo(methodOn(ProductoController.class).productoPorPrecio(precio)).withSelfRel()));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se encontraron productos.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Agregar un producto", description = "Agrega un producto a la base de datos")
    public ResponseEntity<?> agregarProducto(@Valid @RequestBody Producto producto) {
        try {
            ProductosDTO dto = productosService.guardarProductos(producto);
            return ResponseEntity
                .created(linkTo(methodOn(ProductoController.class).productoPorId(dto.getProductoId())).toUri())
                .body(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se guardó el producto.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar un producto", description = "Elimina un producto de la base de datos")
    public ResponseEntity<?> eliminarProducto(@PathVariable Integer id) {
        try {
            String mensaje = productosService.eliminarProducto(id);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar un producto completo", description = "Actualiza todos los campos del producto")
    public ResponseEntity<?> actualizarProducto(@PathVariable Integer id, @Valid @RequestBody Producto producto) {
        try {
            ProductosDTO dto = productosService.actualizarProducto(id, producto);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar un producto parcialmente", description = "Actualiza solo los campos enviados del producto")
    public ResponseEntity<?> patchProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        try {
            ProductosDTO dto = productosService.patchProducto(id, producto);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
