package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.repository.PlatoRepository;
import com.example.demo.service.dto.PlatoDTO;

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
public class PlatoControllerTest {

    @LocalServerPort
	private int port;

    @Autowired
	private TestRestTemplate restTemplate;

    @Autowired
    PlatoRepository platoRepository;

    @Test
    public void given_app_when_get_Platos_then_ok(){

        //Given
        String address = "http://localhost:" + port + "/api/platos/1";

        //When
        ResponseEntity<List<PlatoDTO>> result =
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

    /*@Test
    public void given_app_when_delete_Plato_then_ok(){
        String address = "http://localhost:"+port+"/api/platos/delete/1002";

        this.restTemplate.delete(address);

        then(platoRepository.count()).isEqualTo(4);
    }*/

    @Test
    public void given_app_when_update_Plato_then_ok(){
        String address = "http://localhost:"+port+"/api/platos/update/1005";
        PlatoDTO plato = new PlatoDTO(1L, 1005L, "Torrija", new BigDecimal(5), "", "para Semana Santa", "3");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PlatoDTO> request = new HttpEntity<>(plato,headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);
        
        String expectedResult = "Plato actualizado";

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void given_app_when_add_Plato_then_ok(){
        String address = "http://localhost:"+port+"/api/platos/add";
        PlatoDTO plato = new PlatoDTO(1L, 1006L, "Macarrones", new BigDecimal(12), "", "con tomate", "1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PlatoDTO> request = new HttpEntity<>(plato,headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);
        
        String expectedResult = "plato anadido";

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(expectedResult);
    }
    
}
