package com.example.cafeteriaduoc.msproductos.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Ingrediente")
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ingredienteId;

    @NotBlank(message = "el nombre no puede quedar vacio")
    private String nombreIngrediente;

    @NotNull(message = "La cantidad no puede ser nula")
    private Double cantidadIngrediente;

    @OneToMany(mappedBy = "ingrediente")
    private List<Ingredientes> ingredientes;

    @OneToMany(mappedBy = "ingrediente")
    private List<TiposIngredientes> tiposIngredientes;
}
