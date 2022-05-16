package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.PlatoPedidoTable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoPlatoRepository extends CrudRepository<PlatoPedidoTable, List<Long>> {
    
}