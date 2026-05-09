package com.example.cafeteriaduoc.model;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "pasoReceta")
public class PasoReceta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pasoreceta_id;

    @NotBlank(message = "El título del paso no puede estar vacío")
    private String tituloReceta; //remplaza el nombrePaso pos suena raro

    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcionPaso;

    @OneToMany(mappedBy = "pasoReceta")
    private List<Pasos> pasos;
}
