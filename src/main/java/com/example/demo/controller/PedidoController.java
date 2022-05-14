package com.example.demo.controller;

import com.example.demo.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/add_pedido/{id}")
    public @ResponseBody ResponseEntity<String> addPedido(@PathVariable("id") Long id){

        pedidoService.crearPedido(id);
        
        return ResponseEntity.ok().body("Pedido creado");
    }
    
}
