package com.example.cafeteriaduoc.msventa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Metodopago")
public class Metodopago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer metodopagoId;

    @NotBlank(message = "El tipo de método de pago no puede estar vacío")
    private String tipoMetodoPago;
}
