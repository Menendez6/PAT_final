package com.example.demo.service.dto;

import java.math.BigDecimal;

//Objeto utilizaod para obtener los platos correspondientes a un pedido
//Se obtienen los datos del pedido y de la tabla pedido-plato
public record PedidoPlato(
    String nombre,
    Long num_platos,
    Long id_pedido,
    Long mesa,
    BigDecimal precio,
    Long estado

) {
    
}
