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
@Table("PLATOS")
public class PlatosTable {
    private @Column("PLATO_ID") @Id Long id;
    private @Column("NOMBRE") String nombre;
    private @Column("PRECIO") BigDecimal precio;
    private @Column("FOTO") String foto;
    private @Column("DESCRIPCION") String descripcion;
    private @Column("SECCION") Long seccion;
}
