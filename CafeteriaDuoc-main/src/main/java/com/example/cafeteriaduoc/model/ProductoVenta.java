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
@Table(name = "productoVenta")
public class ProductoVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productoVentaId;
    
    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Ventas venta;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Productos producto;
}
