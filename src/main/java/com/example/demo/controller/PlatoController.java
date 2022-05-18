package com.example.demo.controller;

import java.util.List;

import com.example.demo.service.PlatoService;
import com.example.demo.service.dto.PlatoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Todas las funciones para manipular la información correspondiente a los platos
@RestController
@RequestMapping("/api")
public class PlatoController {

    @Autowired
    private PlatoService platoService;

    //Obtener todos los platos de un restaurante por id del restaurante
    @GetMapping("/platos/{id}")
    public ResponseEntity<List<PlatoDTO>> getPlatos(@PathVariable("id") Long id){

        var platos = platoService.getPlatos(id);
        
        return ResponseEntity.ok().body(platos);
    }

    //Eliminar un plato por id del plato
    @DeleteMapping("/platos/delete/{id}")
    public @ResponseBody ResponseEntity<String> deletePlato(@PathVariable("id") Long id){

       platoService.deletePlato(id);

       return ResponseEntity.ok().body("plato eliminado");
    }

    //Modificar los atributos de un plato
    @PostMapping("/platos/update/{id}")
    public @ResponseBody ResponseEntity<String> updatePlato(@RequestBody PlatoDTO plato){
        platoService.updatePlato(plato);

        return ResponseEntity.ok().body("Plato actualizado");

    }

    //Añadir un plato a la tabla
    //Además se añade a la tabla de restaurante_plato
    @PostMapping("/platos/add")
    public @ResponseBody ResponseEntity<String> addPlato(@RequestBody PlatoDTO plato){
        platoService.addPlato(plato);

        return ResponseEntity.ok().body("plato anadido");
    }

}
