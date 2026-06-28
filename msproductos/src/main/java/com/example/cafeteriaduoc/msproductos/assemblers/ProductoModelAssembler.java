package com.example.cafeteriaduoc.msproductos.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.cafeteriaduoc.msproductos.DTO.ProductosDTO;
import com.example.cafeteriaduoc.msproductos.controller.ProductoController;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<ProductosDTO, EntityModel<ProductosDTO>>{
    @Override
    public EntityModel<ProductosDTO> toModel(ProductosDTO producto) {
        return EntityModel.of(producto,
            linkTo(methodOn(ProductoController.class).productoPorId(producto.getProductoId())).withSelfRel(),
            linkTo(methodOn(ProductoController.class).todosLosProductos()).withRel("productos"),
            linkTo(methodOn(ProductoController.class).eliminarProducto(producto.getProductoId())).withRel("eliminar"),
            linkTo(methodOn(ProductoController.class).actualizarProducto(producto.getProductoId(), null)).withRel("actualizar"),
            linkTo(methodOn(ProductoController.class).patchProducto(producto.getProductoId(), null)).withRel("patch"));
    }
}