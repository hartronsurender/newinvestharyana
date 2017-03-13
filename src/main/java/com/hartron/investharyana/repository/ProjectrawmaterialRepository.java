package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projectrawmaterial;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Projectrawmaterial entity.
 */
@Repository
public class ProjectrawmaterialRepository {

    private final Session session;

    private Mapper<Projectrawmaterial> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ProjectrawmaterialRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectrawmaterial.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectrawmaterial");
        this.truncateStmt = session.prepare("TRUNCATE projectrawmaterial");
    }

    public List<Projectrawmaterial> findAll() {
        List<Projectrawmaterial> projectrawmaterialsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Projectrawmaterial projectrawmaterial = new Projectrawmaterial();
                projectrawmaterial.setId(row.getUUID("id"));
                projectrawmaterial.setProjectid(row.getUUID("projectid"));
                projectrawmaterial.setRaw_material(row.getString("raw_material"));
                projectrawmaterial.setRaw_quantity(row.getInt("raw_quantity"));
                projectrawmaterial.setRaw_unit(row.getUUID("raw_unit"));
                return projectrawmaterial;
            }
        ).forEach(projectrawmaterialsList::add);
        return projectrawmaterialsList;
    }

    public Projectrawmaterial findOne(UUID id) {
        return mapper.get(id);
    }

    public Projectrawmaterial save(Projectrawmaterial projectrawmaterial) {
        if (projectrawmaterial.getId() == null) {
            projectrawmaterial.setId(UUID.randomUUID());
        }
        mapper.save(projectrawmaterial);
        return projectrawmaterial;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
