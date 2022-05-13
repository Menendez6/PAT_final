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
@Table("RESTAURANTES")
public class RestauranteTable {
    private @Column("RESTAURANTE_ID") @Id Long id;
    private @Column("NOMBRE") String mesa;
    private @Column("DIRECCION") String direccion;
}
