package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projectcategory;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Projectcategory entity.
 */
@Repository
public class ProjectcategoryRepository {

    private final Session session;

    private Mapper<Projectcategory> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ProjectcategoryRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectcategory.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectcategory");
        this.truncateStmt = session.prepare("TRUNCATE projectcategory");
    }

    public List<Projectcategory> findAll() {
        List<Projectcategory> projectcategoriesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Projectcategory projectcategory = new Projectcategory();
                projectcategory.setId(row.getUUID("id"));
                projectcategory.setProjectcategoryname(row.getString("projectcategoryname"));
                return projectcategory;
            }
        ).forEach(projectcategoriesList::add);
        return projectcategoriesList;
    }

    public Projectcategory findOne(UUID id) {
        return mapper.get(id);
    }

    public Projectcategory save(Projectcategory projectcategory) {
        if (projectcategory.getId() == null) {
            projectcategory.setId(UUID.randomUUID());
        }
        mapper.save(projectcategory);
        return projectcategory;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
