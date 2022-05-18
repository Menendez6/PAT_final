package com.example.demo.controller;

import java.util.List;

import com.example.demo.service.RestService;
import com.example.demo.service.dto.RestDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Todas las funciones para manipular la información correspondiente a los restaurantes
@RestController
@RequestMapping("/api")
public class RestaurantController {

    @Autowired
    private RestService restService;

    //Obtener todos los restaurantes
    @GetMapping("/restaurants")
    public ResponseEntity<List<RestDTO>> getRestaurants(){

        var restaurants = restService.getRestaurants();
        
        return ResponseEntity.ok().body(restaurants);
    }

    //Obtener el id del restaurante
    @GetMapping("/restaurants/{id}")
    public ResponseEntity<RestDTO> getRestaurantById(@PathVariable("id") Long id){
        var restaurant = restService.getRestById(id);
        return ResponseEntity.ok().body(restaurant);
    }
    
}
