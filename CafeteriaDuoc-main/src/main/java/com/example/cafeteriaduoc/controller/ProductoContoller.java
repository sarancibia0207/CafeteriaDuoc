package com.example.cafeteriaduoc.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.cafeteriaduoc.service.ProductosService;

@RestController
@RequestMapping("/api/v1/producto")
public class ProductoContoller {
    @Autowired
    private ProductosService productosService;
}
