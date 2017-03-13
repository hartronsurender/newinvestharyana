package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Landzoneclassification;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Landzoneclassification entity.
 */
@Repository
public class LandzoneclassificationRepository {

    private final Session session;

    private Mapper<Landzoneclassification> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public LandzoneclassificationRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Landzoneclassification.class);
        this.findAllStmt = session.prepare("SELECT * FROM landzoneclassification");
        this.truncateStmt = session.prepare("TRUNCATE landzoneclassification");
    }

    public List<Landzoneclassification> findAll() {
        List<Landzoneclassification> landzoneclassificationsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Landzoneclassification landzoneclassification = new Landzoneclassification();
                landzoneclassification.setId(row.getUUID("id"));
                landzoneclassification.setLand_zone_classification(row.getString("land_zone_classification"));
                return landzoneclassification;
            }
        ).forEach(landzoneclassificationsList::add);
        return landzoneclassificationsList;
    }

    public Landzoneclassification findOne(UUID id) {
        return mapper.get(id);
    }

    public Landzoneclassification save(Landzoneclassification landzoneclassification) {
        if (landzoneclassification.getId() == null) {
            landzoneclassification.setId(UUID.randomUUID());
        }
        mapper.save(landzoneclassification);
        return landzoneclassification;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
