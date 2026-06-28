package com.example.cafeteriaduoc.msventa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "metodos_pago")
public class Metodospago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer metodosPago_id;

    @ManyToOne
    @JoinColumn(name = "metodo_pago_id")
    private Metodopago metodopago;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Ventas venta;
}
