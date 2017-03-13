package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projectphase;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Projectphase entity.
 */
@Repository
public class ProjectphaseRepository {

    private final Session session;

    private Mapper<Projectphase> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ProjectphaseRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectphase.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectphase");
        this.truncateStmt = session.prepare("TRUNCATE projectphase");
    }

    public List<Projectphase> findAll() {
        List<Projectphase> projectphasesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Projectphase projectphase = new Projectphase();
                projectphase.setId(row.getUUID("id"));
                projectphase.setProjectid(row.getUUID("projectid"));
                projectphase.setFciphase(row.getString("fciphase"));
                projectphase.setFci_product_category(row.getString("fci_product_category"));
                projectphase.setFci(row.getString("fci"));
                projectphase.setFic_implementation_date(row.get("fic_implementation_date", ZonedDateTime.class));
                return projectphase;
            }
        ).forEach(projectphasesList::add);
        return projectphasesList;
    }

    public Projectphase findOne(UUID id) {
        return mapper.get(id);
    }

    public Projectphase save(Projectphase projectphase) {
        if (projectphase.getId() == null) {
            projectphase.setId(UUID.randomUUID());
        }
        mapper.save(projectphase);
        return projectphase;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
