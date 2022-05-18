package com.example.demo.service.dto;

//Extrare la info de platopedido
public record PlatoPedidoDTO(
    Long id_pedido,
    Long id_plato,
    Long num_platos
) {
    
}
