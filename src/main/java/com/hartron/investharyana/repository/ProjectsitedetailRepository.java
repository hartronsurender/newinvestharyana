package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projectsitedetail;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Projectsitedetail entity.
 */
@Repository
public class ProjectsitedetailRepository {

    private final Session session;

    private Mapper<Projectsitedetail> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ProjectsitedetailRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectsitedetail.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectsitedetail");
        this.truncateStmt = session.prepare("TRUNCATE projectsitedetail");
    }

    public List<Projectsitedetail> findAll() {
        List<Projectsitedetail> projectsitedetailsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Projectsitedetail projectsitedetail = new Projectsitedetail();
                projectsitedetail.setId(row.getUUID("id"));
                projectsitedetail.setProjectid(row.getUUID("projectid"));
                projectsitedetail.setCertifiedownership(row.getBool("certifiedownership"));
                projectsitedetail.setOwnership_document(row.getBytes("ownership_document"));
                projectsitedetail.setOwnership_documentContentType(row.getString("ownership_document_content_type"));

                projectsitedetail.setLeaseapplicable(row.getBool("leaseapplicable"));
                projectsitedetail.setLease_document(row.getBytes("lease_document"));
                projectsitedetail.setLease_documentContentType(row.getString("lease_document_content_type"));

                projectsitedetail.setLandagreementapplicable(row.getBool("landagreementapplicable"));
                projectsitedetail.setLandagreement_document(row.getBytes("landagreement_document"));
                projectsitedetail.setLandagreement_documentContentType(row.getString("landagreement_document_content_type"));

                projectsitedetail.setSiteplan_document(row.getBytes("siteplan_document"));
                projectsitedetail.setSiteplan_documentContentType(row.getString("siteplan_document_content_type"));

                projectsitedetail.setLocationplan_document(row.getBytes("locationplan_document"));
                projectsitedetail.setLocationplan_documentContentType(row.getString("locationplan_document_content_type"));

                projectsitedetail.setLinearstripplan_document(row.getBytes("linearstripplan_document"));
                projectsitedetail.setLinearstripplan_documentContentType(row.getString("linearstripplan_document_content_type"));

                projectsitedetail.setConnectingroadid(row.getUUID("connectingroadid"));
                projectsitedetail.setIntersection_distance(row.getBool("intersection_distance"));
                projectsitedetail.setRailway_distance(row.getBool("railway_distance"));
                projectsitedetail.setConfirmity_landuse(row.getBool("confirmity_landuse"));
                projectsitedetail.setLandzone_usetype(row.getUUID("landzone_usetype"));
                projectsitedetail.setSitesituated_document(row.getBytes("sitesituated_document"));
                projectsitedetail.setSitesituated_documentContentType(row.getString("sitesituated_document_content_type"));

                projectsitedetail.setBuilding_existed(row.getUUID("building_existed"));
                projectsitedetail.setExisting_building_applicable(row.getBool("existing_building_applicable"));
                projectsitedetail.setBuilding_plan_document(row.getBytes("building_plan_document"));
                projectsitedetail.setBuilding_plan_documentContentType(row.getString("building_plan_document_content_type"));

                projectsitedetail.setSitesituatedincontrolledarea(row.getBool("sitesituatedincontrolledarea"));
                projectsitedetail.setControlled_area_document(row.getBytes("controlled_area_document"));
                projectsitedetail.setControlled_area_documentContentType(row.getString("controlled_area_document_content_type"));

                return projectsitedetail;
            }
        ).forEach(projectsitedetailsList::add);
        return projectsitedetailsList;
    }

    public Projectsitedetail findOne(UUID id) {
        return mapper.get(id);
    }

    public Projectsitedetail save(Projectsitedetail projectsitedetail) {
        if (projectsitedetail.getId() == null) {
            projectsitedetail.setId(UUID.randomUUID());
        }
        mapper.save(projectsitedetail);
        return projectsitedetail;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
