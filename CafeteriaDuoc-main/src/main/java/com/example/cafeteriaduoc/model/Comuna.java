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
@Table(name = "comuna")
public class Comuna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comunaId;

    @NotBlank(message = "El nombre de la comuna no puede estar vacío")
    private String nombreComuna;

    @OneToMany(mappedBy = "comuna")
    private List<Cafeteria> cafeteria;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

}