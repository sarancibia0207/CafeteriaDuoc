package com.example.cafeteriaduoc.DTO;

import java.util.List;

import lombok.Data;

@Data
public class RegionDTO {
    private Integer regionId;
    private String nombreRegion;
    private List<String> nombresComunas;
}
