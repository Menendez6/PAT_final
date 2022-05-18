package com.example.demo.service.dto;

//Extraer los datos de un restaurante
public record RestDTO(
    Long id,
    String restName,
    String direccion,
    String foto
) {
    
}
