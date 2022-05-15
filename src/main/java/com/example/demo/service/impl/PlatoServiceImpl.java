package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.repository.PlatoRepository;
import com.example.demo.service.PlatoService;
import com.example.demo.service.dto.PlatoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PlatoServiceImpl implements PlatoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatoRepository platoRepo;

    @Override
    public List<PlatoDTO> getPlatos(Long id_rest) {
        String query = 
        """
        SELECT REST_PLATO.RESTAURANTE_ID, PLATOS.PLATO_ID, PLATOS.NOMBRE, PLATOS.PRECIO, PLATOS.FOTO, PLATOS.DESCRIPCION, PLATOS.SECCION
        FROM REST_PLATO
        RIGHT JOIN PLATOS ON (REST_PLATO.PLATO_ID = PLATOS.PLATO_ID)
        """;

        List<PlatoDTO> joinList = jdbcTemplate.query(
            query,
            (rs,rowNum) ->
                    new PlatoDTO(rs.getLong("RESTAURANTE_ID"), rs.getLong("PLATO_ID"), rs.getString("NOMBRE"), rs.getBigDecimal("PRECIO"), rs.getString("FOTO"),rs.getString("DESCRIPCION"),rs.getString("SECCION")));
        return joinList;
    }

    @Override
    public void deletePlato(Long id) {
        jdbcTemplate.execute("DELETE FROM PLATOS WHERE PLATO_ID='"+id+"'");
    }

    @Override
    public void updatePlato(PlatoDTO plato) {
        Long id = plato.id_plato();
        String name = plato.nombre();
        BigDecimal precio = plato.precio();
        String foto = plato.foto();
        String descripcion = plato.descripcion();
        String seccion = plato.seccion();
        jdbcTemplate.execute("UPDATE PLATOS SET NOMBRE ='"+name+"',PRECIO="+precio+",FOTO='"+foto+"',DESCRIPCION='"+descripcion+"',SECCION='"+seccion+"' WHERE PLATO_ID="+id);
    }

    @Override
    public void addPlato(PlatoDTO plato) {
        Long id_rest = plato.id_rest();
        Long id = plato.id_plato();
        String name = plato.nombre();
        BigDecimal precio = plato.precio();
        String foto = plato.foto();
        String descripcion = plato.descripcion();
        String seccion = plato.seccion();
        jdbcTemplate.execute("INSERT INTO PLATOS (PLATO_ID,NOMBRE,PRECIO,FOTO,DESCRIPCION,SECCION) VALUES ("+id+",'"+name+"',"+precio+",'"+foto+"','"+descripcion+"','"+seccion+"');");
        jdbcTemplate.execute("INSERT INTO REST_PLATO (RESTAURANTE_ID,PLATO_ID) VALUES ("+id_rest+","+id+");");
    }
}