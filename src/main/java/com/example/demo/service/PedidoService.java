package com.example.demo.service;

import java.util.List;

import com.example.demo.service.dto.IdDTO;
import com.example.demo.service.dto.PedidoDTO;
import com.example.demo.service.dto.PedidoDTO2;
import com.example.demo.service.dto.PlatoPedidoDTO;

import org.springframework.stereotype.Service;

@Service
public interface PedidoService {
    
    void crearPedido(PedidoDTO pedido);

    IdDTO getId();

    void addPlatoPedido(PlatoPedidoDTO plato);

    List<PlatoPedidoDTO> getPlatosPedidos();

    PedidoDTO2 getPedidoById(Long id);

    List<PedidoDTO2> getPedidoByMesa(Long mesa);
}
