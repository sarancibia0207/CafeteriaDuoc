package com.example.cafeteriaduoc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pasos")
public class Pasos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pasos_id;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
    
    @ManyToOne
    @JoinColumn(name = "pasoreceta_id")
    private PasoReceta pasoReceta;
}
