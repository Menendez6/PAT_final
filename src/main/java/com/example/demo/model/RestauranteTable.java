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
    private @Column("NOMBRE") String restName;
    private @Column("DIRECCION") String direccion;
    private @Column("FOTO") String foto;

    public Long getId() {
        return id;
    }
    public String getRestName() {
        return restName;
    }
    public String getDireccion() {
        return direccion;
    }
    public String getFoto() {
        return foto;
    } 
}
