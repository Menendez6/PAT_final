package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("PEDIDOPLATO")
public class PlatoPedidoTable {
    private @Column("PEDIDO_ID") @Id Long id_pedido;
    private @Column("PLATO_ID") @Id Long id_plato;
    private @Column("NUM_PLATOS") Long num;
}
