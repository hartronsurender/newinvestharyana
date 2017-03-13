package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Financeinvestment;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Financeinvestment entity.
 */
@Repository
public class FinanceinvestmentRepository {

    private final Session session;

    private Mapper<Financeinvestment> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public FinanceinvestmentRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Financeinvestment.class);
        this.findAllStmt = session.prepare("SELECT * FROM financeinvestment");
        this.truncateStmt = session.prepare("TRUNCATE financeinvestment");
    }

    public List<Financeinvestment> findAll() {
        List<Financeinvestment> financeinvestmentsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Financeinvestment financeinvestment = new Financeinvestment();
                financeinvestment.setId(row.getUUID("id"));
                financeinvestment.setProjectid(row.getUUID("projectid"));
                financeinvestment.setLand_cost(row.getDecimal("land_cost"));
                financeinvestment.setBuilding_cost(row.getDecimal("building_cost"));
                financeinvestment.setPlan_machinery_cost(row.getDecimal("plan_machinery_cost"));
                financeinvestment.setMisc_assets(row.getDecimal("misc_assets"));
                financeinvestment.setTotalproject_cost(row.getDecimal("totalproject_cost"));
                financeinvestment.setFdi_applicable(row.getBool("fdi_applicable"));
                financeinvestment.setFdi_country(row.getUUID("fdi_country"));
                financeinvestment.setFdi_value(row.getDecimal("fdi_value"));
                financeinvestment.setFdi_resource(row.getUUID("fdi_resource"));
                financeinvestment.setProject_construction_start_date(row.get("project_construction_start_date", ZonedDateTime.class));
                financeinvestment.setProject_commercial_activity_date(row.get("project_commercial_activity_date", ZonedDateTime.class));
                return financeinvestment;
            }
        ).forEach(financeinvestmentsList::add);
        return financeinvestmentsList;
    }

    public Financeinvestment findOne(UUID id) {
        return mapper.get(id);
    }

    public Financeinvestment save(Financeinvestment financeinvestment) {
        if (financeinvestment.getId() == null) {
            financeinvestment.setId(UUID.randomUUID());
        }
        mapper.save(financeinvestment);
        return financeinvestment;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
