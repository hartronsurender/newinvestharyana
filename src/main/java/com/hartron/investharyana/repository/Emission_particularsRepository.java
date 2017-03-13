package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Emission_particulars;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Emission_particulars entity.
 */
@Repository
public class Emission_particularsRepository {

    private final Session session;

    private Mapper<Emission_particulars> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Emission_particularsRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Emission_particulars.class);
        this.findAllStmt = session.prepare("SELECT * FROM emission_particulars");
        this.truncateStmt = session.prepare("TRUNCATE emission_particulars");
    }

    public List<Emission_particulars> findAll() {
        List<Emission_particulars> emission_particularsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Emission_particulars emission_particulars = new Emission_particulars();
                emission_particulars.setId(row.getUUID("id"));
                emission_particulars.setParticular_type(row.getString("particular_type"));
                return emission_particulars;
            }
        ).forEach(emission_particularsList::add);
        return emission_particularsList;
    }

    public Emission_particulars findOne(UUID id) {
        return mapper.get(id);
    }

    public Emission_particulars save(Emission_particulars emission_particulars) {
        if (emission_particulars.getId() == null) {
            emission_particulars.setId(UUID.randomUUID());
        }
        mapper.save(emission_particulars);
        return emission_particulars;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
