package com.example.demo.controller;

import java.util.List;

import com.example.demo.service.RestService;
import com.example.demo.service.dto.RestDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestaurantController {

    @Autowired
    private RestService restService;

    @GetMapping("/restaurants")
    public ResponseEntity<List<RestDTO>> getRestaurants(){

        var restaurants = restService.getRestaurants();
        
        return ResponseEntity.ok().body(restaurants);
    }
    
}
