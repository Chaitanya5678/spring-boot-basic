package com.askchaitanya.blackops.services;

import com.askchaitanya.blackops.config.DataSource;
import com.askchaitanya.blackops.models.Gadget;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
@Log4j2
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

    public List<Gadget> listGadgets() {
        String sql = "SELECT * FROM blackops.gadgets";
        List<Gadget> gadgetsList = null;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            gadgetsList = new ArrayList<>();
            while (rs.next()) {
                gadgetsList.add(Gadget.builder()
                        .id(UUID.fromString(rs.getString(1)))
                        .brand(rs.getString(2))
                        .name(rs.getString(3))
                        .price(rs.getLong(4))
                        .color(rs.getString(5))
                        .build()
                );
            }
        } catch (SQLException e) {
            log.error("Exception occurred while fetching gadgets", e);
        }
        return gadgetsList;
    }
}
