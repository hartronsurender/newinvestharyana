package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Emission_air_pollution;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Emission_air_pollution entity.
 */
@Repository
public class Emission_air_pollutionRepository {

    private final Session session;

    private Mapper<Emission_air_pollution> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Emission_air_pollutionRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Emission_air_pollution.class);
        this.findAllStmt = session.prepare("SELECT * FROM emission_air_pollution");
        this.truncateStmt = session.prepare("TRUNCATE emission_air_pollution");
    }

    public List<Emission_air_pollution> findAll() {
        List<Emission_air_pollution> emission_air_pollutionsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Emission_air_pollution emission_air_pollution = new Emission_air_pollution();
                emission_air_pollution.setId(row.getUUID("id"));
                emission_air_pollution.setPollution_controll_device(row.getString("pollution_controll_device"));
                return emission_air_pollution;
            }
        ).forEach(emission_air_pollutionsList::add);
        return emission_air_pollutionsList;
    }

    public Emission_air_pollution findOne(UUID id) {
        return mapper.get(id);
    }

    public Emission_air_pollution save(Emission_air_pollution emission_air_pollution) {
        if (emission_air_pollution.getId() == null) {
            emission_air_pollution.setId(UUID.randomUUID());
        }
        mapper.save(emission_air_pollution);
        return emission_air_pollution;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
