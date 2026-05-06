package com.example.cafeteriaduoc.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//principal en todo el codigo y a tabla
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "producto")

public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer producto_id;
    
    @NotBlank (message = "El nombre del producto no puede estar vacío.")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotNull (message = "El precio del producto no puede ser nulo.")
    private double precio;

    @NotNull (message = "El stock del producto no puede ser nulo.")
    private Integer stock;

    @OneToMany(mappedBy = "producto")
    private List<Tipos> tiposProductos;

    @OneToMany(mappedBy = "producto")
    private List<Tamanos> tamanosProductos;

    @OneToMany(mappedBy = "producto")
    private List<ProductoVenta> productoVenta;

    @OneToMany(mappedBy = "producto")
    private List<Ingredientes> ingredientesProductos;

}