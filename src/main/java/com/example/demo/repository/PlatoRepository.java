package com.example.demo.repository;

import com.example.demo.model.PlatosTable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatoRepository extends CrudRepository<PlatosTable, Long> {
    
}
