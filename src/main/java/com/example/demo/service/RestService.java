package com.example.demo.service;

import java.util.List;

import com.example.demo.service.dto.RestDTO;

import org.springframework.stereotype.Service;

@Service
public interface RestService {

    List<RestDTO> getRestaurants();

    RestDTO getRestById(Long id);
    
}
