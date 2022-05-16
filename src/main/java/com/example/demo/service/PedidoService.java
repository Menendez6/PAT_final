package com.example.demo.service;

import java.util.List;

import com.example.demo.service.dto.*;

import org.springframework.stereotype.Service;

@Service
public interface PedidoService {
    
    void crearPedido(PedidoDTO pedido);

    IdDTO getId();

    void addPlatoPedido(PlatoPedidoDTO plato);

    List<PlatoPedidoDTO> getPlatosPedidos();

    PedidoDTO2 getPedidoById(Long id);

    List<PedidoPlato> getPedidoById2(Long id);

    List<PedidoDTO2> getPedidoByMesa(Long mesa,Long id_rest);

    List<PedidoPlato> getPedidosByRestId(Long id_rest);

    void updatePedido(PedidoEstadoDTO pedido);

    void deletePedido(Long id);
}
