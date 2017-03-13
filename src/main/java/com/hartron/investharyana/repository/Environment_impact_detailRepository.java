package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Environment_impact_detail;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Environment_impact_detail entity.
 */
@Repository
public class Environment_impact_detailRepository {

    private final Session session;

    private Mapper<Environment_impact_detail> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Environment_impact_detailRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Environment_impact_detail.class);
        this.findAllStmt = session.prepare("SELECT * FROM environment_impact_detail");
        this.truncateStmt = session.prepare("TRUNCATE environment_impact_detail");
    }

    public List<Environment_impact_detail> findAll() {
        List<Environment_impact_detail> environment_impact_detailsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Environment_impact_detail environment_impact_detail = new Environment_impact_detail();
                environment_impact_detail.setId(row.getUUID("id"));
                environment_impact_detail.setProjectid(row.getUUID("projectid"));
                environment_impact_detail.setWater_supply_source(row.getUUID("water_supply_source"));
                environment_impact_detail.setWater_usage_process(row.getInt("water_usage_process"));
                environment_impact_detail.setWater_usage_cooling(row.getInt("water_usage_cooling"));
                environment_impact_detail.setWater_usage_domestic(row.getInt("water_usage_domestic"));
                environment_impact_detail.setWater_usage_other(row.getString("water_usage_other"));
                environment_impact_detail.setWater_waste_process(row.getInt("water_waste_process"));
                environment_impact_detail.setWater_waste_cooling(row.getInt("water_waste_cooling"));
                environment_impact_detail.setWater_waste_domesting(row.getInt("water_waste_domesting"));
                environment_impact_detail.setWater_waste_other(row.getString("water_waste_other"));
                environment_impact_detail.setWaste_water_treatment(row.getString("waste_water_treatment"));
                environment_impact_detail.setWaste_water_treatment_document(row.getBytes("waste_water_treatment_document"));
                environment_impact_detail.setWaste_water_treatment_documentContentType(row.getString("waste_water_treatment_document_content_type"));

                environment_impact_detail.setMode_of_disposal(row.getUUID("mode_of_disposal"));
                environment_impact_detail.setEmissionid(row.getUUID("emissionid"));
                environment_impact_detail.setWater_waste_id(row.getUUID("water_waste_id"));
                return environment_impact_detail;
            }
        ).forEach(environment_impact_detailsList::add);
        return environment_impact_detailsList;
    }

    public Environment_impact_detail findOne(UUID id) {
        return mapper.get(id);
    }

    public Environment_impact_detail save(Environment_impact_detail environment_impact_detail) {
        if (environment_impact_detail.getId() == null) {
            environment_impact_detail.setId(UUID.randomUUID());
        }
        mapper.save(environment_impact_detail);
        return environment_impact_detail;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
