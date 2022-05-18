package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.repository.PedidoRepository;
import com.example.demo.service.dto.IdDTO;
import com.example.demo.service.dto.PedidoDTO;
import com.example.demo.service.dto.PedidoDTO2;
import com.example.demo.service.dto.PedidoEstadoDTO;
import com.example.demo.service.dto.PedidoPlato;
import com.example.demo.service.dto.PlatoPedidoDTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

    @Autowired
    private PedidoRepository pedidoRepository;


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
    public void given_app_when_get_PlatosPedidos_then_ok(){

        //Given
        String address = "http://localhost:" + port + "/api/getPlatosPedidos";

        //When
        ResponseEntity<List<PlatoPedidoDTO>> result =
                restTemplate.exchange(
                        address,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {}
                );

        //Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody().size()).isEqualTo(4);
    }

    @Test
    public void given_app_when_add_getPedidoId_then_ok(){
        String address = "http://localhost:"+port+"/api/getPedido/1";
        ResponseEntity<PedidoDTO2> result = this.restTemplate.getForEntity(address, PedidoDTO2.class);
        
        PedidoDTO2 pedido = new PedidoDTO2(1L, 25L, new BigDecimal(29), 2L, 1L);

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(pedido);
    }

    @Test
    public void given_app_when_get_PedidoPlato_then_ok(){

        //Given
        String address = "http://localhost:" + port + "/api/getPedido2/1";

        //When
        ResponseEntity<List<PedidoPlato>> result =
                restTemplate.exchange(
                        address,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {}
                );

        //Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody().size()).isEqualTo(3);
    }

    @Test
    public void given_app_when_get_PedidoMesa_then_ok(){

        //Given
        String address = "http://localhost:" + port + "/api/getPedidoMesa/1/25";

        //When
        ResponseEntity<List<PedidoDTO2>> result =
                restTemplate.exchange(
                        address,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {}
                );

        //Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody().size()).isEqualTo(1);
    }

    @Test
    public void given_app_when_get_PedidoByRestId_then_ok(){

        //Given
        String address = "http://localhost:" + port + "/api/getPedidos/restaurante/1";

        //When
        ResponseEntity<List<PedidoPlato>> result =
                restTemplate.exchange(
                        address,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {}
                );

        //Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody().size()).isEqualTo(5);
    }

    @Test
    public void given_app_when_update_PlatoPedido_then_ok(){
        String address = "http://localhost:"+port+"/api/pedido/update/1";
        PedidoEstadoDTO pedido = new PedidoEstadoDTO(1l, 3L);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PedidoEstadoDTO> request = new HttpEntity<>(pedido,headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);
        
        String expectedResult = "pedido actualizado";

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(expectedResult);
    }

    //No lo ejecuto porque al borrar me cambia el resto de tests, pero funciona
    /*@Test
    public void given_app_when_delete_Pedido_then_ok(){
        String address = "http://localhost:"+port+"/api/pedido/1";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Long> request = new HttpEntity<>(1L,headers);

        this.restTemplate.delete(address);
        
        String expectedResult = "pedido eliminado";

        then(pedidoRepository.count()).isEqualTo(4);
    }*/



}
