package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Water_supply_source;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Water_supply_source entity.
 */
@Repository
public class Water_supply_sourceRepository {

    private final Session session;

    private Mapper<Water_supply_source> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Water_supply_sourceRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Water_supply_source.class);
        this.findAllStmt = session.prepare("SELECT * FROM water_supply_source");
        this.truncateStmt = session.prepare("TRUNCATE water_supply_source");
    }

    public List<Water_supply_source> findAll() {
        List<Water_supply_source> water_supply_sourcesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Water_supply_source water_supply_source = new Water_supply_source();
                water_supply_source.setId(row.getUUID("id"));
                water_supply_source.setSource_type(row.getString("source_type"));
                return water_supply_source;
            }
        ).forEach(water_supply_sourcesList::add);
        return water_supply_sourcesList;
    }

    public Water_supply_source findOne(UUID id) {
        return mapper.get(id);
    }

    public Water_supply_source save(Water_supply_source water_supply_source) {
        if (water_supply_source.getId() == null) {
            water_supply_source.setId(UUID.randomUUID());
        }
        mapper.save(water_supply_source);
        return water_supply_source;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
