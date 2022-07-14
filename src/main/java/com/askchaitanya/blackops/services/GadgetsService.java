package com.askchaitanya.blackops.services;

import com.askchaitanya.blackops.models.Gadget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class GadgetsService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Optional<Gadget> getGadgetById(UUID id) {
        String sql = "SELECT * FROM blackops.gadgets WHERE id = ?";
        Gadget gadget;
        try {
            gadget = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> Gadget.builder()
                    .id(UUID.fromString(rs.getString(1)))
                    .brand(rs.getString(2))
                    .name(rs.getString(3))
                    .price(rs.getLong(4))
                    .color(rs.getString(5))
                    .build());
        } catch (EmptyResultDataAccessException e) {
            gadget = null;
        }
        if (Objects.nonNull(gadget)) {
            return Optional.of(gadget);
        } else {
            return Optional.empty();
        }
    }
}
