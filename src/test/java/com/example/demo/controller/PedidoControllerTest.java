package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.service.dto.IdDTO;
import com.example.demo.service.dto.PedidoDTO;
import com.example.demo.service.dto.PedidoDTO2;
import com.example.demo.service.dto.PlatoPedidoDTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PedidoControllerTest {
    
    @LocalServerPort
	private int port;

    @Autowired
	private TestRestTemplate restTemplate;

    @Test
    public void given_app_when_add_Pedido_then_ok(){
        String address = "http://localhost:"+port+"/api/add_pedido";
        PedidoDTO pedido = new PedidoDTO(30L, new BigDecimal(73), 1L);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PedidoDTO> request = new HttpEntity<>(pedido,headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);
        
        String expectedResult = "Pedido creado";

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void given_app_when_add_getId_then_ok(){
        String address = "http://localhost:"+port+"/api/getId";
        ResponseEntity<IdDTO> result = this.restTemplate.getForEntity(address, IdDTO.class);
        
        IdDTO id = new IdDTO(0L);

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(id);
    }

    @Test
    public void given_app_when_add_PlatoPedido_then_ok(){
        String address = "http://localhost:"+port+"/api/addPlatoPedido";
        PlatoPedidoDTO plato = new PlatoPedidoDTO(2L, 1002L, 3l);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PlatoPedidoDTO> request = new HttpEntity<>(plato,headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);
        
        String expectedResult = "Platos añadidos";

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void given_app_when_add_getPedidoId_then_ok(){
        String address = "http://localhost:"+port+"/api/getPedido/1";
        ResponseEntity<PedidoDTO2> result = this.restTemplate.getForEntity(address, PedidoDTO2.class);
        
        PedidoDTO2 pedido = new PedidoDTO2(1L, 25L, new BigDecimal(29), 2L, 1L);

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(pedido);
    }


}