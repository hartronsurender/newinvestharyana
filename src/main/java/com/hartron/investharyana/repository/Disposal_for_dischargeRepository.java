package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Disposal_for_discharge;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Disposal_for_discharge entity.
 */
@Repository
public class Disposal_for_dischargeRepository {

    private final Session session;

    private Mapper<Disposal_for_discharge> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Disposal_for_dischargeRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Disposal_for_discharge.class);
        this.findAllStmt = session.prepare("SELECT * FROM disposal_for_discharge");
        this.truncateStmt = session.prepare("TRUNCATE disposal_for_discharge");
    }

    public List<Disposal_for_discharge> findAll() {
        List<Disposal_for_discharge> disposal_for_dischargesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Disposal_for_discharge disposal_for_discharge = new Disposal_for_discharge();
                disposal_for_discharge.setId(row.getUUID("id"));
                disposal_for_discharge.setDisposal_for_discharge_type(row.getString("disposal_for_discharge_type"));
                return disposal_for_discharge;
            }
        ).forEach(disposal_for_dischargesList::add);
        return disposal_for_dischargesList;
    }

    public Disposal_for_discharge findOne(UUID id) {
        return mapper.get(id);
    }

    public Disposal_for_discharge save(Disposal_for_discharge disposal_for_discharge) {
        if (disposal_for_discharge.getId() == null) {
            disposal_for_discharge.setId(UUID.randomUUID());
        }
        mapper.save(disposal_for_discharge);
        return disposal_for_discharge;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
