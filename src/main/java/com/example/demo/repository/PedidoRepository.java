package com.example.demo.repository;

import com.example.demo.model.PedidoTable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository<PedidoTable, Long> {
    
}
