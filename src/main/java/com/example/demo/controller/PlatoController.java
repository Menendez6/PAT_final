package com.example.demo.controller;

import java.util.List;

import com.example.demo.service.PlatoService;
import com.example.demo.service.dto.PlatoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/platos/delete/{id}")
    public @ResponseBody ResponseEntity<String> deletePlato(@PathVariable("id") Long id){

       platoService.deletePlato(id);

       return ResponseEntity.ok().body("plato eliminado");
    }

    @PostMapping("/platos/update/{id}")
    public @ResponseBody ResponseEntity<String> updatePlato(@RequestBody PlatoDTO plato){
        platoService.updatePlato(plato);

        return ResponseEntity.ok().body("Plato actualizado");

    }

    @PostMapping("/platos/add")
    public @ResponseBody ResponseEntity<String> addPlato(@RequestBody PlatoDTO plato){
        platoService.addPlato(plato);

        return ResponseEntity.ok().body("plato anadido");
    }

}
