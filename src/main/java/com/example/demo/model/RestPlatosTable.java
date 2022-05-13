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
@Table("RESTPLATOS")
public class RestPlatosTable {
    private @Column("RESTAURANTE_ID") @Id Long id_rest;
    private @Column("PLATO_ID") @Id Long id_plato;
}
