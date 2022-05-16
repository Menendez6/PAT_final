package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import com.example.demo.model.PedidoTable;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.service.PedidoService;
import com.example.demo.service.dto.*;

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
        jdbcTemplate.execute("INSERT INTO PEDIDOS (MESA, PRECIO, ESTADO, RESTAURANTE_ID) VALUES ("+pedido.mesa()+","+pedido.precio()+","+1+","+pedido.id_restaurante()+")");
        
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
        PedidoDTO2 pedido = new PedidoDTO2(table.getId(), table.getMesa(), table.getPrecio(), table.getEstado(),table.getIdRestaurante());
        return pedido;
    }


    @Override
    public List<PedidoPlato> getPedidoById2(Long id) {
        String query = 
        """
        SELECT PLATOS.NOMBRE, PEDIDO_PLATO.NUM_PLATOS, PEDIDOS.PEDIDO_ID, PEDIDOS.MESA, PEDIDOS.PRECIO, PEDIDOS.ESTADO
        FROM PEDIDO_PLATO
        RIGHT JOIN PEDIDOS ON (PEDIDO_PLATO.PEDIDO_ID = PEDIDOS.PEDIDO_ID)
        RIGHT JOIN PLATOS ON (PLATOS.PLATO_ID = PEDIDO_PLATO.PLATO_ID)
        WHERE PEDIDOS.PEDIDO_ID = """ +id;


        List<PedidoPlato> joinList = jdbcTemplate.query(
            query,
            (rs,rowNum) ->
                    new PedidoPlato(rs.getString("NOMBRE"),rs.getLong("NUM_PLATOS"),rs.getLong("PEDIDO_ID"), rs.getLong("MESA"), rs.getBigDecimal("PRECIO"),rs.getLong("ESTADO")));
        return joinList;
    }

    @Override
    public List<PedidoDTO2> getPedidoByMesa(Long mesa,Long id_rest) {
        String query = "SELECT * FROM PEDIDOS WHERE (mesa ="+mesa+") AND (RESTAURANTE_ID =" +id_rest+")";
        
        List<PedidoDTO2> joinList = jdbcTemplate.query(
            query,
            (rs,rowNum) ->
                    new PedidoDTO2(rs.getLong("PEDIDO_ID"), rs.getLong("MESA"), rs.getBigDecimal("PRECIO"),rs.getLong("ESTADO"),rs.getLong("RESTAURANTE_ID")));
        return joinList;
    }

    @Override
    public List<PedidoPlato> getPedidosByRestId(Long id_rest) {
        String query =
                """
                SELECT PLATOS.NOMBRE, PEDIDO_PLATO.NUM_PLATOS, PEDIDOS.PEDIDO_ID, PEDIDOS.MESA, PEDIDOS.PRECIO, PEDIDOS.ESTADO
                FROM PEDIDO_PLATO
                RIGHT JOIN PEDIDOS ON (PEDIDO_PLATO.PEDIDO_ID = PEDIDOS.PEDIDO_ID)
                RIGHT JOIN PLATOS ON (PLATOS.PLATO_ID = PEDIDO_PLATO.PLATO_ID)
                WHERE PEDIDOS.RESTAURANTE_ID = """ +id_rest;

        List<PedidoPlato> joinList = jdbcTemplate.query(
                query,
                (rs,rowNum) ->
                        new PedidoPlato(rs.getString("NOMBRE"),rs.getLong("NUM_PLATOS"),rs.getLong("PEDIDO_ID"), rs.getLong("MESA"), rs.getBigDecimal("PRECIO"),rs.getLong("ESTADO")));
        return joinList;
    }

    @Override
    public void updatePedido(PedidoEstadoDTO pedido) {
        Long id = pedido.id_pedido();
        Long estado = pedido.estado();
        jdbcTemplate.execute("UPDATE PEDIDOS SET ESTADO="+estado+" WHERE PEDIDO_ID="+id);
    }

    @Override
    public void deletePedido(Long id) {
        jdbcTemplate.execute("DELETE FROM PEDIDOS WHERE PEDIDO_ID='"+id+"'");
    }

}
