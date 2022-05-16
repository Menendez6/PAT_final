package com.example.demo.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("PEDIDOS")
public class PedidoTable {
    private @Column("PEDIDO_ID") @Id Long id;
    private @Column("MESA") Long mesa;
    private @Column("PRECIO") BigDecimal precio;
    private @Column("ESTADO") Long estado;
    private @Column("RESTAURANTE_ID") Long id_restaurante;

    public Long getId() {
        return id;
    }
    public Long getMesa() {
        return mesa;
    }
    public BigDecimal getPrecio() {
        return precio;
    }
    public Long getEstado() {
        return estado;
    } 

    public Long getIdRestaurante(){
        return id_restaurante;
    }
}
