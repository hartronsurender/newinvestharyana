package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Emissiondetail;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Emissiondetail entity.
 */
@Repository
public class EmissiondetailRepository {

    private final Session session;

    private Mapper<Emissiondetail> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public EmissiondetailRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Emissiondetail.class);
        this.findAllStmt = session.prepare("SELECT * FROM emissiondetail");
        this.truncateStmt = session.prepare("TRUNCATE emissiondetail");
    }

    public List<Emissiondetail> findAll() {
        List<Emissiondetail> emissiondetailsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Emissiondetail emissiondetail = new Emissiondetail();
                emissiondetail.setId(row.getUUID("id"));
                emissiondetail.setProjectid(row.getUUID("projectid"));
                emissiondetail.setParticulars(row.getUUID("particulars"));
                emissiondetail.setCapacity(row.getString("capacity"));
                emissiondetail.setType_of_fuel(row.getUUID("type_of_fuel"));
                emissiondetail.setAir_pollution_control_device(row.getUUID("air_pollution_control_device"));
                return emissiondetail;
            }
        ).forEach(emissiondetailsList::add);
        return emissiondetailsList;
    }

    public Emissiondetail findOne(UUID id) {
        return mapper.get(id);
    }

    public Emissiondetail save(Emissiondetail emissiondetail) {
        if (emissiondetail.getId() == null) {
            emissiondetail.setId(UUID.randomUUID());
        }
        mapper.save(emissiondetail);
        return emissiondetail;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
