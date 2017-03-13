package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.District;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the District entity.
 */
@Repository
public class DistrictRepository {

    private final Session session;

    private Mapper<District> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public DistrictRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(District.class);
        this.findAllStmt = session.prepare("SELECT * FROM district");
        this.truncateStmt = session.prepare("TRUNCATE district");
    }

    public List<District> findAll() {
        List<District> districtsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                District district = new District();
                district.setId(row.getUUID("id"));
                district.setStateid(row.getUUID("stateid"));
                district.setDistrictname(row.getString("districtname"));
                return district;
            }
        ).forEach(districtsList::add);
        return districtsList;
    }

    public District findOne(UUID id) {
        return mapper.get(id);
    }

    public District save(District district) {
        if (district.getId() == null) {
            district.setId(UUID.randomUUID());
        }
        mapper.save(district);
        return district;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
