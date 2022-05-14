package com.example.demo.service.impl;

import com.example.demo.model.PedidoTable;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PedidoRepository pedidoRepo;
    
    @Override
    public void crearPedido(Long id) {
        jdbcTemplate.execute("INSERT INTO PEDIDOS (PEDIDO_ID, PRECIO, ESTADO) VALUES ("+id+","+0+","+0+")");
        
    }
    
}
