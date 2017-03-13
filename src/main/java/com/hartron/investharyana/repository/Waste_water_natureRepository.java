package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Waste_water_nature;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Waste_water_nature entity.
 */
@Repository
public class Waste_water_natureRepository {

    private final Session session;

    private Mapper<Waste_water_nature> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Waste_water_natureRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Waste_water_nature.class);
        this.findAllStmt = session.prepare("SELECT * FROM waste_water_nature");
        this.truncateStmt = session.prepare("TRUNCATE waste_water_nature");
    }

    public List<Waste_water_nature> findAll() {
        List<Waste_water_nature> waste_water_naturesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Waste_water_nature waste_water_nature = new Waste_water_nature();
                waste_water_nature.setId(row.getUUID("id"));
                waste_water_nature.setWaste_water_nature_type(row.getString("waste_water_nature_type"));
                return waste_water_nature;
            }
        ).forEach(waste_water_naturesList::add);
        return waste_water_naturesList;
    }

    public Waste_water_nature findOne(UUID id) {
        return mapper.get(id);
    }

    public Waste_water_nature save(Waste_water_nature waste_water_nature) {
        if (waste_water_nature.getId() == null) {
            waste_water_nature.setId(UUID.randomUUID());
        }
        mapper.save(waste_water_nature);
        return waste_water_nature;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
