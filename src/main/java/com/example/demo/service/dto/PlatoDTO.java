package com.example.demo.service.dto;

import java.math.BigDecimal;

public record PlatoDTO(
    Long id_rest,
    Long id_plato,
    String nombre,
    BigDecimal precio,
    String foto,
    String descripcion,
    String seccion
) {
    
}
