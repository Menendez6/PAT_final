package com.example.demo.service.dto;

import java.math.BigDecimal;

public record PedidoDTO(
    Long mesa,
    BigDecimal precio,
    Long id_restaurante
) {
    
}
