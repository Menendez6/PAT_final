package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.List;

import com.example.demo.service.dto.RestDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestauranteControllerTest {

    @LocalServerPort
	private int port;

    @Autowired
	private TestRestTemplate restTemplate;

    @Test
    public void given_app_when_get_Restaurants_then_ok(){

        //Given
        String address = "http://localhost:" + port + "/api/restaurants";

        //When
        ResponseEntity<List<RestDTO>> result =
                restTemplate.exchange(
                        address,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {}
                );

        //Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody().size()).isEqualTo(2);
    }

    @Test
    public void given_app_when_get_RestaurantById_then_ok(){

        //Given
        String address = "http://localhost:" + port + "/api/restaurants/1";

        //When
        ResponseEntity<RestDTO> result =
                restTemplate.exchange(
                        address,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {}
                );

        RestDTO expected = new RestDTO(1L,"Casa Pablo","Calle Pechofrio Sainz 55","https://static.guiarepsol.com/fichas-gr/media/thumbnails/filer_public/b0/13/b0131f7f-888b-4a6a-b27d-c21cc0160d3a/tmptmp7kqjkuij8c2016f7a3114746bf45a7cf32afd033_1284x850_q75_middle.jpeg");
        //Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(expected);
    }
    
}
