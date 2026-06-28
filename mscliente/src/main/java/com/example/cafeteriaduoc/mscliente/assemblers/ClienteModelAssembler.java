package com.example.cafeteriaduoc.mscliente.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.cafeteriaduoc.mscliente.DTO.ClienteDTO;
import com.example.cafeteriaduoc.mscliente.controller.ClienteController;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<ClienteDTO, EntityModel<ClienteDTO>>{
    @Override
    public EntityModel<ClienteDTO> toModel(ClienteDTO cliente) {
        return EntityModel.of(cliente,
            linkTo(methodOn(ClienteController.class).clientePorId(cliente.getClienteId())).withSelfRel(),
            linkTo(methodOn(ClienteController.class).todosLosClientes()).withRel("clientes"),
            linkTo(methodOn(ClienteController.class).eliminarCliente(cliente.getClienteId())).withRel("eliminar"),
            linkTo(methodOn(ClienteController.class).clientePorNombre(cliente.getNombreCliente())).withRel("nombre"),
            linkTo(methodOn(ClienteController.class).actualizarCliente(cliente.getClienteId(), null)).withRel("actualizar"),
            linkTo(methodOn(ClienteController.class).patchCliente(cliente.getClienteId(), null)).withRel("patch")
        );
    }
}
