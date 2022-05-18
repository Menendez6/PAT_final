package com.example.demo.service.dto;

import java.math.BigDecimal;

//Extraer toda la info de la tabla platos
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
