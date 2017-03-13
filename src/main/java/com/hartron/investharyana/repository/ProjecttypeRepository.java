package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projecttype;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Projecttype entity.
 */
@Repository
public class ProjecttypeRepository {

    private final Session session;

    private Mapper<Projecttype> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ProjecttypeRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projecttype.class);
        this.findAllStmt = session.prepare("SELECT * FROM projecttype");
        this.truncateStmt = session.prepare("TRUNCATE projecttype");
    }

    public List<Projecttype> findAll() {
        List<Projecttype> projecttypesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Projecttype projecttype = new Projecttype();
                projecttype.setId(row.getUUID("id"));
                projecttype.setProjecttype_name(row.getString("projecttype_name"));
                return projecttype;
            }
        ).forEach(projecttypesList::add);
        return projecttypesList;
    }

    public Projecttype findOne(UUID id) {
        return mapper.get(id);
    }

    public Projecttype save(Projecttype projecttype) {
        if (projecttype.getId() == null) {
            projecttype.setId(UUID.randomUUID());
        }
        mapper.save(projecttype);
        return projecttype;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
