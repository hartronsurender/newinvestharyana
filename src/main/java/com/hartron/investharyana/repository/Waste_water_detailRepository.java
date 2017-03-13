package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Waste_water_detail;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Waste_water_detail entity.
 */
@Repository
public class Waste_water_detailRepository {

    private final Session session;

    private Mapper<Waste_water_detail> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Waste_water_detailRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Waste_water_detail.class);
        this.findAllStmt = session.prepare("SELECT * FROM waste_water_detail");
        this.truncateStmt = session.prepare("TRUNCATE waste_water_detail");
    }

    public List<Waste_water_detail> findAll() {
        List<Waste_water_detail> waste_water_detailsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Waste_water_detail waste_water_detail = new Waste_water_detail();
                waste_water_detail.setId(row.getUUID("id"));
                waste_water_detail.setProjectid(row.getUUID("projectid"));
                waste_water_detail.setSource_of_generation(row.getString("source_of_generation"));
                waste_water_detail.setNature_type(row.getUUID("nature_type"));
                waste_water_detail.setQuantity(row.getString("quantity"));
                waste_water_detail.setMode_of_disposal(row.getUUID("mode_of_disposal"));
                return waste_water_detail;
            }
        ).forEach(waste_water_detailsList::add);
        return waste_water_detailsList;
    }

    public Waste_water_detail findOne(UUID id) {
        return mapper.get(id);
    }

    public Waste_water_detail save(Waste_water_detail waste_water_detail) {
        if (waste_water_detail.getId() == null) {
            waste_water_detail.setId(UUID.randomUUID());
        }
        mapper.save(waste_water_detail);
        return waste_water_detail;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
