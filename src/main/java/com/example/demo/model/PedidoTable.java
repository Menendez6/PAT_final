package com.example.demo.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("PEDIDOS")
public class PedidoTable {
    private @Column("PEDIDO_ID") @Id Long id;
    private @Column("MESA") Long mesa;
    private @Column("PRECIO") BigDecimal precio;
    private @Column("ESTADO") Long estado;
}
