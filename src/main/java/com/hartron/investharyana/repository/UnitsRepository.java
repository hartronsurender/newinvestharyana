package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Units;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Units entity.
 */
@Repository
public class UnitsRepository {

    private final Session session;

    private Mapper<Units> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public UnitsRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Units.class);
        this.findAllStmt = session.prepare("SELECT * FROM units");
        this.truncateStmt = session.prepare("TRUNCATE units");
    }

    public List<Units> findAll() {
        List<Units> unitsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Units units = new Units();
                units.setId(row.getUUID("id"));
                units.setUnittype(row.getString("unittype"));
                units.setDescription(row.getString("description"));
                return units;
            }
        ).forEach(unitsList::add);
        return unitsList;
    }

    public Units findOne(UUID id) {
        return mapper.get(id);
    }

    public Units save(Units units) {
        if (units.getId() == null) {
            units.setId(UUID.randomUUID());
        }
        mapper.save(units);
        return units;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
