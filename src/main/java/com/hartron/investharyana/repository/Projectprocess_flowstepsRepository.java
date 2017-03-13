package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projectprocess_flowsteps;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Projectprocess_flowsteps entity.
 */
@Repository
public class Projectprocess_flowstepsRepository {

    private final Session session;

    private Mapper<Projectprocess_flowsteps> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Projectprocess_flowstepsRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectprocess_flowsteps.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectprocess_flowsteps");
        this.truncateStmt = session.prepare("TRUNCATE projectprocess_flowsteps");
    }

    public List<Projectprocess_flowsteps> findAll() {
        List<Projectprocess_flowsteps> projectprocess_flowstepsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Projectprocess_flowsteps projectprocess_flowsteps = new Projectprocess_flowsteps();
                projectprocess_flowsteps.setId(row.getUUID("id"));
                projectprocess_flowsteps.setProjectid(row.getUUID("projectid"));
                projectprocess_flowsteps.setProcess_flow_document(row.getBytes("process_flow_document"));
                projectprocess_flowsteps.setProcess_flow_documentContentType(row.getString("process_flow_document_content_type"));

                projectprocess_flowsteps.setSteps(row.getString("steps"));
                return projectprocess_flowsteps;
            }
        ).forEach(projectprocess_flowstepsList::add);
        return projectprocess_flowstepsList;
    }

    public Projectprocess_flowsteps findOne(UUID id) {
        return mapper.get(id);
    }

    public Projectprocess_flowsteps save(Projectprocess_flowsteps projectprocess_flowsteps) {
        if (projectprocess_flowsteps.getId() == null) {
            projectprocess_flowsteps.setId(UUID.randomUUID());
        }
        mapper.save(projectprocess_flowsteps);
        return projectprocess_flowsteps;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
