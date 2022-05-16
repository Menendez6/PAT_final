package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.repository.PlatoRepository;
import com.example.demo.service.dto.PlatoDTO;
import com.example.demo.service.dto.PlatoPedidoDTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.BDDAssertions.then;

@Transactional
@SpringBootTest
public class PlatoServiceTest {

    @Autowired
    private PlatoService platoService;

    @Autowired
    private PlatoRepository platoRepository;

    @Test
    public void given_service_when_call_getPlatosPedidos_then_Ok(){
        List<PlatoDTO> platos = platoService.getPlatos(1L);

        then(platos.get(0)).isEqualTo(new PlatoDTO(1L, 1001L, "Jamon Iberico", new BigDecimal(18), "https://cdn.shopify.com/s/files/1/1589/5089/files/Jamon-iberico_1024x1024.jpg?v=1526041502", "Servido con pan tumaca", "1"));
    }
}
