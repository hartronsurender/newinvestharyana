package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Manufactur_unit;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Manufactur_unit entity.
 */
@Repository
public class Manufactur_unitRepository {

    private final Session session;

    private Mapper<Manufactur_unit> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Manufactur_unitRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Manufactur_unit.class);
        this.findAllStmt = session.prepare("SELECT * FROM manufactur_unit");
        this.truncateStmt = session.prepare("TRUNCATE manufactur_unit");
    }

    public List<Manufactur_unit> findAll() {
        List<Manufactur_unit> manufactur_unitsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Manufactur_unit manufactur_unit = new Manufactur_unit();
                manufactur_unit.setId(row.getUUID("id"));
                manufactur_unit.setUnit_type(row.getString("unit_type"));
                return manufactur_unit;
            }
        ).forEach(manufactur_unitsList::add);
        return manufactur_unitsList;
    }

    public Manufactur_unit findOne(UUID id) {
        return mapper.get(id);
    }

    public Manufactur_unit save(Manufactur_unit manufactur_unit) {
        if (manufactur_unit.getId() == null) {
            manufactur_unit.setId(UUID.randomUUID());
        }
        mapper.save(manufactur_unit);
        return manufactur_unit;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
