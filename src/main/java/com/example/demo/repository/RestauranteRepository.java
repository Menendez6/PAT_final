package com.example.demo.repository;

import com.example.demo.model.RestauranteTable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends CrudRepository<RestauranteTable, Long>{
}
