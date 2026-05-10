package com.example.cafeteriaduoc.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cafeteriaduoc.service.TamanosService;

@RestController
@RequestMapping("/api/v1/tamanos")
public class TamanosController {
    @Autowired
    private TamanosService tamanosService;
}
