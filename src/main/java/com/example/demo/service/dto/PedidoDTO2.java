package com.example.demo.service.dto;

import java.math.BigDecimal;

//Objeto para recuperar todos los datos de la tabla de pedidos
public record PedidoDTO2(
    Long id_pedido,
    Long mesa,
    BigDecimal precio,
    Long estado,
    Long id_restaurante

) {
    
}