package com.example.cafeteriaduoc.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cafeteria")
public class Cafeteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cafeteria_id; 

    @OneToMany(mappedBy = "cafeteria")
    private List<Ventas> ventas;

    @NotBlank(message = "El nombre de la cafetería no puede estar vacío")
    private String nombreCafeteria;

    @ManyToOne
    @JoinColumn(name = "comuna_id")
    private Comuna comuna;

}
