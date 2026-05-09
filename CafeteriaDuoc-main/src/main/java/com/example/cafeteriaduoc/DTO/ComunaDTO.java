package com.example.cafeteriaduoc.DTO;

import java.util.List;

import lombok.Data;

@Data
public class ComunaDTO {
    private Integer comunaId;
    private String nombreComuna;
    private String nombreRegion;
    private List<String> nombreCafeterias;
}
