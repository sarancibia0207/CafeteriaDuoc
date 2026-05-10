package com.example.cafeteriaduoc.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.OneToMany;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//resumen la boleta
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ventas")
public class Ventas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ventaId;

    @NotNull(message = "El total de la venta no puede ser nulo")
    private Double totalVenta;

    @NotNull(message = "La fecha de la venta no puede ser nula")
    private Date fechaVenta;

    @OneToMany(mappedBy = "venta")
    private List<ProductoVenta> productoVenta;

    @OneToMany(mappedBy = "venta")
    private List<Metodospago> metodosPago;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "cafeteria_id")
    private Cafeteria cafeteria;
}