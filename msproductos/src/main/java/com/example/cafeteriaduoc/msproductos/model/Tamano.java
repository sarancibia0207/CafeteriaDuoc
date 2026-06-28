package com.example.cafeteriaduoc.msproductos.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tamano")
public class Tamano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tamanoId;

    @NotBlank(message = "El nombre del tamaño no puede estar vacío")
    private String nombreTamano;

    @OneToMany(mappedBy = "tamano")
    private List<Tamanos> tamanos;
}
