package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.StreamSupport;

import com.example.demo.repository.RestauranteRepository;
import com.example.demo.service.RestService;
import com.example.demo.service.dto.RestDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RestServiceImpl implements RestService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RestauranteRepository restRepo;

    
    @Override
    public List<RestDTO> getRestaurants() {
        return StreamSupport.stream(restRepo.findAll().spliterator(),false)
            .map(obj -> new RestDTO(
                obj.getId(),
                obj.getRestName(),
                obj.getDireccion(),
                obj.getFoto()))
            .toList();
    }
    
}
