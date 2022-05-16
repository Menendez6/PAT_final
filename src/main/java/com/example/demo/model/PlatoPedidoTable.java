package com.example.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("PEDIDO_PLATO")
public class PlatoPedidoTable {
    private @Column("PEDIDO_ID") Long id_pedido;
    private @Column("PLATO_ID") Long id_plato;
    private @Column("NUM_PLATOS") Long num;

    @Id List<Long> keys;

    public Long getIdPedido() {
        return id_pedido;
    }
    public Long getIdPlato() {
        return id_plato;
    }
    public Long getNum() {
        return num;
    }
}
