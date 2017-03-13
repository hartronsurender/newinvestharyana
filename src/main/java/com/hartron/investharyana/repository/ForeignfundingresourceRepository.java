package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Foreignfundingresource;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Foreignfundingresource entity.
 */
@Repository
public class ForeignfundingresourceRepository {

    private final Session session;

    private Mapper<Foreignfundingresource> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ForeignfundingresourceRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Foreignfundingresource.class);
        this.findAllStmt = session.prepare("SELECT * FROM foreignfundingresource");
        this.truncateStmt = session.prepare("TRUNCATE foreignfundingresource");
    }

    public List<Foreignfundingresource> findAll() {
        List<Foreignfundingresource> foreignfundingresourcesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Foreignfundingresource foreignfundingresource = new Foreignfundingresource();
                foreignfundingresource.setId(row.getUUID("id"));
                foreignfundingresource.setForeign_funding_resource_type(row.getString("foreign_funding_resource_type"));
                return foreignfundingresource;
            }
        ).forEach(foreignfundingresourcesList::add);
        return foreignfundingresourcesList;
    }

    public Foreignfundingresource findOne(UUID id) {
        return mapper.get(id);
    }

    public Foreignfundingresource save(Foreignfundingresource foreignfundingresource) {
        if (foreignfundingresource.getId() == null) {
            foreignfundingresource.setId(UUID.randomUUID());
        }
        mapper.save(foreignfundingresource);
        return foreignfundingresource;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
