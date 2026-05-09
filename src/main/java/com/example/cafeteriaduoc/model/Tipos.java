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
@Table(name = "tipos")
public class Tipos {
//many to many
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tipos;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Productos producto;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private Tipo tipo;
}
