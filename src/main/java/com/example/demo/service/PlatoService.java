package com.example.demo.service;

import java.util.List;

import com.example.demo.service.dto.PlatoDTO;

import org.springframework.stereotype.Service;

@Service
public interface PlatoService {
    
    List<PlatoDTO> getPlatos(Long id_rest);
    void deletePlato(Long id);
    void updatePlato(PlatoDTO plato);
}
