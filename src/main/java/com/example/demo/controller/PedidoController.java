package com.example.demo.controller;

import java.util.List;

import com.example.demo.service.PedidoService;
import com.example.demo.service.dto.IdDTO;
import com.example.demo.service.dto.PedidoDTO;
import com.example.demo.service.dto.PlatoPedidoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/add_pedido")
    public @ResponseBody ResponseEntity<String> addPedido(@RequestBody PedidoDTO pedido){

        pedidoService.crearPedido(pedido);
        
        return ResponseEntity.ok().body("Pedido creado");
    }

    @GetMapping("/getId")
    public ResponseEntity<IdDTO> getId(){

        IdDTO id = pedidoService.getId();
        
        return ResponseEntity.ok().body(id);
    }

    @PostMapping("/addPlatoPedido")
    public @ResponseBody ResponseEntity<String> addPlatoPedido(@RequestBody PlatoPedidoDTO plato){

        pedidoService.addPlatoPedido(plato);
        
        return ResponseEntity.ok().body("Platos añadidos");
    }

    @GetMapping("/getPlatosPedidos")
    public ResponseEntity<List<PlatoPedidoDTO>> getPlatosPedidos(){

        List<PlatoPedidoDTO> platos = pedidoService.getPlatosPedidos();
        
        return ResponseEntity.ok().body(platos);
    }

    
}
