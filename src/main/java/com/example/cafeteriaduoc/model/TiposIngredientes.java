package com.example.cafeteriaduoc.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tiposIngrediente")
public class TiposIngredientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tiposIngredientes_id;

    @ManyToOne
    @JoinColumn(name = "tipoIngrediente_id")
    private TipoIngrediente tipoIngrediente;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;
}