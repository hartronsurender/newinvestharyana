package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Emission_fuel_type;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Emission_fuel_type entity.
 */
@Repository
public class Emission_fuel_typeRepository {

    private final Session session;

    private Mapper<Emission_fuel_type> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Emission_fuel_typeRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Emission_fuel_type.class);
        this.findAllStmt = session.prepare("SELECT * FROM emission_fuel_type");
        this.truncateStmt = session.prepare("TRUNCATE emission_fuel_type");
    }

    public List<Emission_fuel_type> findAll() {
        List<Emission_fuel_type> emission_fuel_typesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Emission_fuel_type emission_fuel_type = new Emission_fuel_type();
                emission_fuel_type.setId(row.getUUID("id"));
                emission_fuel_type.setFuel_type(row.getString("fuel_type"));
                return emission_fuel_type;
            }
        ).forEach(emission_fuel_typesList::add);
        return emission_fuel_typesList;
    }

    public Emission_fuel_type findOne(UUID id) {
        return mapper.get(id);
    }

    public Emission_fuel_type save(Emission_fuel_type emission_fuel_type) {
        if (emission_fuel_type.getId() == null) {
            emission_fuel_type.setId(UUID.randomUUID());
        }
        mapper.save(emission_fuel_type);
        return emission_fuel_type;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
