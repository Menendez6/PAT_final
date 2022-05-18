package com.example.demo.service.dto;

//Objeto utilizado para modificar el estado de un pedido
public record PedidoEstadoDTO(
        Long id_pedido,
        Long estado
) {

}