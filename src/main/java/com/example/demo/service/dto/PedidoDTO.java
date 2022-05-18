package com.example.demo.service.dto;

import java.math.BigDecimal;

//Objeto para introducir en la tabla solo los datos de mesa, precio e id restaurante
public record PedidoDTO(
    Long mesa,
    BigDecimal precio,
    Long id_restaurante
) {
    
}
