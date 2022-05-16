package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.repository.PedidoPlatoRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.service.dto.IdDTO;
import com.example.demo.service.dto.PedidoDTO;
import com.example.demo.service.dto.PedidoDTO2;
import com.example.demo.service.dto.PedidoPlato;
import com.example.demo.service.dto.PlatoPedidoDTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.BDDAssertions.then;

@Transactional
@SpringBootTest
public class PedidoServiceTest {
    
    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoPlatoRepository pedidoPlatoRepository;

    @Test
    public void given_service_when_call_getId_then_Ok(){
        PedidoDTO pedido = new PedidoDTO(30L, new BigDecimal(20), 1L);
        pedidoService.crearPedido(pedido);

        IdDTO id = pedidoService.getId();
        

        then(id.id()).isEqualTo(3L);
    }

    @Test
    public void given_service_when_call_crearPedido_then_Ok(){
        PedidoDTO pedido = new PedidoDTO(30L, new BigDecimal(20), 1L);
        pedidoService.crearPedido(pedido);

        then(pedidoRepository.count()).isEqualTo(2);
    }

    @Test 
    public void given_service_when_call_addPlato_then_Ok(){
        PlatoPedidoDTO plato = new PlatoPedidoDTO(1L, 1001L, 4L);
        pedidoService.addPlatoPedido(plato);

        then(pedidoPlatoRepository.count()).isEqualTo(4);
    }

    @Test
    public void given_service_when_call_getPlatosPedidos_then_Ok(){
        List<PlatoPedidoDTO> platos = pedidoService.getPlatosPedidos();

        then(platos.get(0)).isEqualTo(new PlatoPedidoDTO(1L, 1002L, 1L));
    }

    @Test void given_service_when_call_getPedidoById_then_Ok(){

        PedidoDTO2 pedido = pedidoService.getPedidoById(1L);

        then(pedido).isEqualTo(new PedidoDTO2(1L, 25L,new BigDecimal(29) , 2L, 1L));
    }

    @Test void given_service_when_call_getPedidoById2_then_Ok(){

        List<PedidoPlato> pedidos = pedidoService.getPedidoById2(1L);

        then(pedidos.get(0)).isEqualTo(new PedidoPlato("Patatas Bravas", 1L, 1L, 25L,new BigDecimal(29), 2L));
    }

    @Test void given_service_when_call_getPedidoByMesa_then_Ok(){

        List<PedidoDTO2> pedidos = pedidoService.getPedidoByMesa(25L,1L);

        then(pedidos.get(0)).isEqualTo(new PedidoDTO2(1L, 25L, new BigDecimal(29), 2L, 1L));
    }

    
}
