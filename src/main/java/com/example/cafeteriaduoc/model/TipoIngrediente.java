package com.example.cafeteriaduoc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import jakarta.persistence.OneToMany;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tipoIngrediente")
public class TipoIngrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tipoIngrediente_id;

    @NotBlank(message = "El tipo de ingrediente no puede estar vacío")
    private String nombreTipoIngrediente;

    @OneToMany(mappedBy = "tipoIngrediente")
    private List<Ingredientes> ingredientes;

}
