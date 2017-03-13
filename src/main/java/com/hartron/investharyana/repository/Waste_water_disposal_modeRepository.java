package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Waste_water_disposal_mode;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Waste_water_disposal_mode entity.
 */
@Repository
public class Waste_water_disposal_modeRepository {

    private final Session session;

    private Mapper<Waste_water_disposal_mode> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Waste_water_disposal_modeRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Waste_water_disposal_mode.class);
        this.findAllStmt = session.prepare("SELECT * FROM waste_water_disposal_mode");
        this.truncateStmt = session.prepare("TRUNCATE waste_water_disposal_mode");
    }

    public List<Waste_water_disposal_mode> findAll() {
        List<Waste_water_disposal_mode> waste_water_disposal_modesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Waste_water_disposal_mode waste_water_disposal_mode = new Waste_water_disposal_mode();
                waste_water_disposal_mode.setId(row.getUUID("id"));
                waste_water_disposal_mode.setDisposal_mode(row.getString("disposal_mode"));
                return waste_water_disposal_mode;
            }
        ).forEach(waste_water_disposal_modesList::add);
        return waste_water_disposal_modesList;
    }

    public Waste_water_disposal_mode findOne(UUID id) {
        return mapper.get(id);
    }

    public Waste_water_disposal_mode save(Waste_water_disposal_mode waste_water_disposal_mode) {
        if (waste_water_disposal_mode.getId() == null) {
            waste_water_disposal_mode.setId(UUID.randomUUID());
        }
        mapper.save(waste_water_disposal_mode);
        return waste_water_disposal_mode;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
