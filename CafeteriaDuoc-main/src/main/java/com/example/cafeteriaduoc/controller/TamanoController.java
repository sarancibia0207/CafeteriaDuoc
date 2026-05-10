package com.example.cafeteriaduoc.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cafeteriaduoc.service.TamanoService;

@RestController
@RequestMapping("/api/v1/tamano")
public class TamanoController {
    @Autowired
    private TamanoService tamanoService;
}
