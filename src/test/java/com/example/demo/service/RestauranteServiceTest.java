package com.example.demo.service;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

import com.example.demo.repository.RestauranteRepository;
import com.example.demo.service.dto.RestDTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class RestauranteServiceTest {
    
    @Autowired
    private RestService restService;

    @Autowired
    private RestauranteRepository restRepository;

    @Test
    public void given_service_when_call_getRestaurantes_then_Ok(){
        List<RestDTO> restaurantes = restService.getRestaurants();

        then(restaurantes.get(0)).isEqualTo(new RestDTO(1L, "Casa Pablo", "Calle Pechofrio Sainz 55", "https://static.guiarepsol.com/fichas-gr/media/thumbnails/filer_public/b0/13/b0131f7f-888b-4a6a-b27d-c21cc0160d3a/tmptmp7kqjkuij8c2016f7a3114746bf45a7cf32afd033_1284x850_q75_middle.jpeg"));
    }

    @Test
    public void given_service_when_call_getRestauranteById_then_Ok(){
        RestDTO restaurante = restService.getRestById(1L);

        then(restaurante).isEqualTo(new RestDTO(1L, "Casa Pablo", "Calle Pechofrio Sainz 55", "https://static.guiarepsol.com/fichas-gr/media/thumbnails/filer_public/b0/13/b0131f7f-888b-4a6a-b27d-c21cc0160d3a/tmptmp7kqjkuij8c2016f7a3114746bf45a7cf32afd033_1284x850_q75_middle.jpeg"));
    }

}
