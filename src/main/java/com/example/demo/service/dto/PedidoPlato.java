package com.example.demo.service.dto;

import java.math.BigDecimal;

public record PedidoPlato(
    String nombre,
    Long num_platos,
    Long id_pedido,
    Long mesa,
    BigDecimal precio,
    Long estado

) {
    
}
