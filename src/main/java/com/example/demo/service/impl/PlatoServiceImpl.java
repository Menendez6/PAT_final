package com.example.demo.service.impl;

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
    public void deletePlatos(Long id) {
        jdbcTemplate.execute("DELETE FROM PLATOS WHERE PLATO_ID='"+id+"'");
    }
    
}
