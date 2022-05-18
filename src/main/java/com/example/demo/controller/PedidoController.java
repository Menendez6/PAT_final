package com.example.demo.controller;

import java.util.List;

import com.example.demo.service.PedidoService;
import com.example.demo.service.dto.*;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Todas las funciones para manipular la información correspondiente a los pedidos
@RestController
@RequestMapping("/api")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    //Añadir un nuevo pedido a la tabla
    @PostMapping("/add_pedido")
    public @ResponseBody ResponseEntity<String> addPedido(@RequestBody PedidoDTO pedido){

        pedidoService.crearPedido(pedido);
        
        return ResponseEntity.ok().body("Pedido creado");
    }

    //Obtener el id del último pedido añadido
    //Necesario cuando creamos un pedido para saber cual es el id del pedido que se acaba de hacer
    @GetMapping("/getId")
    public ResponseEntity<IdDTO> getId(){

        IdDTO id = pedidoService.getId();
        
        return ResponseEntity.ok().body(id);
    }

    //Añadir un elemento a la tabla plato-pedidos que guarda la info de los platos correspondientes a un pedido
    @PostMapping("/addPlatoPedido")
    public @ResponseBody ResponseEntity<String> addPlatoPedido(@RequestBody PlatoPedidoDTO plato){

        pedidoService.addPlatoPedido(plato);
        
        return ResponseEntity.ok().body("Platos añadidos");
    }

    //Obtener toda la tabla de plato-pedido para comprobar que se guarda bien
    @GetMapping("/getPlatosPedidos")
    public ResponseEntity<List<PlatoPedidoDTO>> getPlatosPedidos(){

        List<PlatoPedidoDTO> platos = pedidoService.getPlatosPedidos();
        
        return ResponseEntity.ok().body(platos);
    }

    //Obtener un pedido por su Id
    //Obtenemos únicamente el pedido, sin los platos
    @GetMapping("/getPedido/{id}")
    public ResponseEntity<PedidoDTO2> getPedido(@PathVariable("id") Long id){
        
        var pedido = pedidoService.getPedidoById(id);
        
        return ResponseEntity.ok().body(pedido);
    }

    //Obtener un pedido por su id 
    //La diferencia con el anterior es que en esta obtenemos el pedido con los platos
    @GetMapping("/getPedido2/{id}")
    public ResponseEntity<List<PedidoPlato>> getPedidoPlato(@PathVariable("id") Long id){
        
        var pedido = pedidoService.getPedidoById2(id);
        
        return ResponseEntity.ok().body(pedido);
    }

    //Obtener todos los pedidos de una mesa de un restaurante
    //Una mesa puede tener varios pedidos
    @GetMapping("/getPedidoMesa/{id}/{mesa}")
    public ResponseEntity<List<PedidoDTO2>> getPedidoMesa(@PathVariable("mesa") Long mesa,@PathVariable("id") Long id){
        
        var pedido = pedidoService.getPedidoByMesa(mesa,id);
        
        return ResponseEntity.ok().body(pedido);
    }

    //Obtener todos los pedidos de un restaurante
    @GetMapping("/getPedidos/restaurante/{id}")
    public ResponseEntity<List<PedidoPlato>> getPedidoByRestId(@PathVariable("id") Long id){

        var pedidos = pedidoService.getPedidosByRestId(id);

        return ResponseEntity.ok().body(pedidos);
    }

    //Actualizar el estado de un pedido
    @PostMapping("/pedido/update/{id}")
    public @ResponseBody ResponseEntity<String> updatePedido(@RequestBody PedidoEstadoDTO pedido){
        pedidoService.updatePedido(pedido);

        return ResponseEntity.ok().body("pedido actualizado");

    }

    //Eliminar un pedido de la tabla
    @DeleteMapping("/pedido/{id}")
    public @ResponseBody ResponseEntity<String> deletePedido(@PathVariable("id") Long id){

        pedidoService.deletePedido(id);

        return ResponseEntity.ok().body("pedido eliminado");
    }
    
}
