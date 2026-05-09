package com.example.cafeteriaduoc.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cliente_id;

    @NotBlank (message= "el nombre no puede quedar vacio")
    private String nombreCliente;

    @NotBlank (message = "El RUT del cliente no puede quedar vacío")
    private String rutCliente;
    
    @NotNull (message = "La fecha no puede ser nula.")
    private Date fechaNacimiento;
    
    @NotNull (message = "Los puntos acumulables no pueden ser nulos")
    private double puntosAcumulables;

    @OneToMany
    private List<Ventas> clienteVenta; 

}
