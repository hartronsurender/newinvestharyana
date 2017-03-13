package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Industrysize;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Industrysize entity.
 */
@Repository
public class IndustrysizeRepository {

    private final Session session;

    private Mapper<Industrysize> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public IndustrysizeRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Industrysize.class);
        this.findAllStmt = session.prepare("SELECT * FROM industrysize");
        this.truncateStmt = session.prepare("TRUNCATE industrysize");
    }

    public List<Industrysize> findAll() {
        List<Industrysize> industrysizesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Industrysize industrysize = new Industrysize();
                industrysize.setId(row.getUUID("id"));
                industrysize.setSize_of_industry(row.getString("size_of_industry"));
                return industrysize;
            }
        ).forEach(industrysizesList::add);
        return industrysizesList;
    }

    public Industrysize findOne(UUID id) {
        return mapper.get(id);
    }

    public Industrysize save(Industrysize industrysize) {
        if (industrysize.getId() == null) {
            industrysize.setId(UUID.randomUUID());
        }
        mapper.save(industrysize);
        return industrysize;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
