package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import com.example.demo.model.PedidoTable;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.service.PedidoService;
import com.example.demo.service.dto.IdDTO;
import com.example.demo.service.dto.PedidoDTO;
import com.example.demo.service.dto.PedidoDTO2;
import com.example.demo.service.dto.PlatoPedidoDTO;

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
    public void crearPedido(PedidoDTO pedido) {
        jdbcTemplate.execute("INSERT INTO PEDIDOS (MESA, PRECIO, ESTADO) VALUES ("+pedido.mesa()+","+pedido.precio()+","+1+")");
        
    }

    @Override
    public IdDTO getId() {
        IdDTO id = jdbcTemplate.queryForObject("SELECT SCOPE_IDENTITY() AS ID",(rs,rowNum) -> new IdDTO(rs.getLong("ID")));
        return id;
        
    }

    @Override
    public void addPlatoPedido(PlatoPedidoDTO plato) {
        jdbcTemplate.execute("INSERT INTO PEDIDO_PLATO (PEDIDO_ID, PLATO_ID, NUM_PLATOS) VALUES ("+plato.id_pedido()+","+plato.id_plato()+","+plato.num_platos()+")");
    }

    @Override
    public List<PlatoPedidoDTO> getPlatosPedidos() {
        String query = "SELECT * FROM PEDIDO_PLATO";
        List<PlatoPedidoDTO> joinList = jdbcTemplate.query(
            query,
            (rs,rowNum) ->
                    new PlatoPedidoDTO(rs.getLong("PEDIDO_ID"), rs.getLong("PLATO_ID"), rs.getLong("NUM_PLATOS")));
        return joinList;
    }

    @Override
    public PedidoDTO2 getPedidoById(Long id) {
        Optional<PedidoTable> opedido = pedidoRepo.findById(id);
        PedidoTable table = opedido.get();
        PedidoDTO2 pedido = new PedidoDTO2(table.getId(), table.getMesa(), table.getPrecio(), table.getEstado());
        return pedido;
    }

    @Override
    public List<PedidoDTO2> getPedidoByMesa(Long mesa) {
        String query = "SELECT * FROM PEDIDOS WHERE MESA = " + mesa;
        return null;
    }
    
    
}
