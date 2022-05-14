package com.example.demo.controller;

import java.util.List;

import com.example.demo.service.PlatoService;
import com.example.demo.service.dto.PlatoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PlatoController {

    @Autowired
    private PlatoService platoService;

    @GetMapping("/platos/{id}")
    public ResponseEntity<List<PlatoDTO>> getPlatos(@PathVariable("id") Long id){

        var platos = platoService.getPlatos(id);
        
        return ResponseEntity.ok().body(platos);
    }
    
}
