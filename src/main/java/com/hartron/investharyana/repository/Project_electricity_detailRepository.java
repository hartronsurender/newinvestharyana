package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Project_electricity_detail;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Project_electricity_detail entity.
 */
@Repository
public class Project_electricity_detailRepository {

    private final Session session;

    private Mapper<Project_electricity_detail> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Project_electricity_detailRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Project_electricity_detail.class);
        this.findAllStmt = session.prepare("SELECT * FROM project_electricity_detail");
        this.truncateStmt = session.prepare("TRUNCATE project_electricity_detail");
    }

    public List<Project_electricity_detail> findAll() {
        List<Project_electricity_detail> project_electricity_detailsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Project_electricity_detail project_electricity_detail = new Project_electricity_detail();
                project_electricity_detail.setId(row.getUUID("id"));
                project_electricity_detail.setProjectid(row.getUUID("projectid"));
                project_electricity_detail.setTemporaryconnection(row.getBytes("temporaryconnection"));
                project_electricity_detail.setTemporaryconnectionContentType(row.getString("temporaryconnection_content_type"));

                project_electricity_detail.setLoad_required(row.getBool("load_required"));
                project_electricity_detail.setExisting_connecton(row.getBool("existing_connecton"));
                project_electricity_detail.setAccount_number(row.getString("account_number"));
                project_electricity_detail.setLoad_demand(row.getDecimal("load_demand"));
                project_electricity_detail.setLoad_demand_kva(row.getDecimal("load_demand_kva"));
                project_electricity_detail.setNew_load_demand_kw(row.getDecimal("new_load_demand_kw"));
                project_electricity_detail.setNew_load_demand_kva(row.getDecimal("new_load_demand_kva"));
                project_electricity_detail.setLoad_demand_date(row.get("load_demand_date", ZonedDateTime.class));
                project_electricity_detail.setRegular_connection(row.getBytes("regular_connection"));
                project_electricity_detail.setRegular_connectionContentType(row.getString("regular_connection_content_type"));

                project_electricity_detail.setRegular_load_required(row.getBool("regular_load_required"));
                project_electricity_detail.setRegular_existing_connection(row.getBool("regular_existing_connection"));
                project_electricity_detail.setRegular_uhbvn_dhbvn_customer_type(row.getUUID("regular_uhbvn_dhbvn_customer_type"));
                project_electricity_detail.setRegular_account_number(row.getString("regular_account_number"));
                project_electricity_detail.setRegular_load_if_any_kw(row.getDecimal("regular_load_if_any_kw"));
                project_electricity_detail.setRegular_existing_load_kva(row.getDecimal("regular_existing_load_kva"));
                project_electricity_detail.setRegular_new_load_demand_kw(row.getDecimal("regular_new_load_demand_kw"));
                project_electricity_detail.setRegular_new_load_demand_kva(row.getDecimal("regular_new_load_demand_kva"));
                project_electricity_detail.setRegular_demand_date(row.get("regular_demand_date", ZonedDateTime.class));
                return project_electricity_detail;
            }
        ).forEach(project_electricity_detailsList::add);
        return project_electricity_detailsList;
    }

    public Project_electricity_detail findOne(UUID id) {
        return mapper.get(id);
    }

    public Project_electricity_detail save(Project_electricity_detail project_electricity_detail) {
        if (project_electricity_detail.getId() == null) {
            project_electricity_detail.setId(UUID.randomUUID());
        }
        mapper.save(project_electricity_detail);
        return project_electricity_detail;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
