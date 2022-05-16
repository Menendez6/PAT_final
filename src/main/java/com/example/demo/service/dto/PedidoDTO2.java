package com.example.demo.service.dto;

import java.math.BigDecimal;

public record PedidoDTO2(
    Long id_pedido,
    Long mesa,
    BigDecimal precio,
    Long estado,
    Long id_restaurante

) {
    
}