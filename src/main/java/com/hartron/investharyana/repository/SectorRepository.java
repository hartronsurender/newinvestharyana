package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Sector;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Sector entity.
 */
@Repository
public class SectorRepository {

    private final Session session;

    private Mapper<Sector> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public SectorRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Sector.class);
        this.findAllStmt = session.prepare("SELECT * FROM sector");
        this.truncateStmt = session.prepare("TRUNCATE sector");
    }

    public List<Sector> findAll() {
        List<Sector> sectorsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Sector sector = new Sector();
                sector.setId(row.getUUID("id"));
                sector.setSectorname(row.getString("sectorname"));
                return sector;
            }
        ).forEach(sectorsList::add);
        return sectorsList;
    }

    public Sector findOne(UUID id) {
        return mapper.get(id);
    }

    public Sector save(Sector sector) {
        if (sector.getId() == null) {
            sector.setId(UUID.randomUUID());
        }
        mapper.save(sector);
        return sector;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
