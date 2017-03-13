package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projectdetail;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Projectdetail entity.
 */
@Repository
public class ProjectdetailRepository {

    private final Session session;

    private Mapper<Projectdetail> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ProjectdetailRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectdetail.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectdetail");
        this.truncateStmt = session.prepare("TRUNCATE projectdetail");
    }

    public List<Projectdetail> findAll() {
        List<Projectdetail> projectdetailsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Projectdetail projectdetail = new Projectdetail();
                projectdetail.setId(row.getUUID("id"));
                projectdetail.setSiteaddress(row.getString("siteaddress"));
                projectdetail.setDistrict(row.getUUID("district"));
                projectdetail.setBlock(row.getUUID("block"));
                projectdetail.setCity_town_village(row.getUUID("city_town_village"));
                projectdetail.setTehsil_subtehsil(row.getUUID("tehsil_subtehsil"));
                projectdetail.setMultyvillageinvolved(row.getBool("multyvillageinvolved"));
                projectdetail.setVillageinvolved(row.getString("villageinvolved"));
                projectdetail.setFalls_in_aravalli(row.getBool("falls_in_aravalli"));
                projectdetail.setLandprocured(row.getBool("landprocured"));
                projectdetail.setAllotedbyhsiidc(row.getBool("allotedbyhsiidc"));
                projectdetail.setEstate(row.getString("estate"));
                projectdetail.setCluster(row.getString("cluster"));
                projectdetail.setPhase(row.getString("phase"));
                projectdetail.setSector(row.getString("sector"));
                projectdetail.setPlotno(row.getString("plotno"));
                projectdetail.setHadbastno(row.getString("hadbastno"));
                projectdetail.setKhasradocument(row.getBytes("khasradocument"));
                projectdetail.setKhasradocumentContentType(row.getString("khasradocument_content_type"));

                projectdetail.setLies_under_mc(row.getBool("lies_under_mc"));
                projectdetail.setDistance_from_mc(row.getInt("distance_from_mc"));
                projectdetail.setLocated_in_urban(row.getBool("located_in_urban"));
                projectdetail.setRevenue_shajra_document(row.getBytes("revenue_shajra_document"));
                projectdetail.setRevenue_shajra_documentContentType(row.getString("revenue_shajra_document_content_type"));

                projectdetail.setJamabandi_document(row.getBytes("jamabandi_document"));
                projectdetail.setJamabandi_documentContentType(row.getString("jamabandi_document_content_type"));

                projectdetail.setNonencumbrancecertificate(row.getBytes("nonencumbrancecertificate"));
                projectdetail.setNonencumbrancecertificateContentType(row.getString("nonencumbrancecertificate_content_type"));

                projectdetail.setTotalproposedprojectarea(row.getDecimal("totalproposedprojectarea"));
                projectdetail.setProposed_build_up_area(row.getDecimal("proposed_build_up_area"));
                projectdetail.setSecotr(row.getUUID("secotr"));
                projectdetail.setProjectpurpose(row.getUUID("projectpurpose"));
                projectdetail.setSize_of_industry(row.getUUID("size_of_industry"));
                projectdetail.setProject_type(row.getUUID("project_type"));
                projectdetail.setNic_code(row.getString("nic_code"));
                projectdetail.setCategory_of_project(row.getUUID("category_of_project"));
                projectdetail.setCollaboration_with_foreign(row.getUUID("collaboration_with_foreign"));
                projectdetail.setDetail_project_report(row.getBytes("detail_project_report"));
                projectdetail.setDetail_project_reportContentType(row.getString("detail_project_report_content_type"));

                projectdetail.setExisting_regulatory_approval(row.getBool("existing_regulatory_approval"));
                projectdetail.setApproval_application_form(row.getUUID("approval_application_form"));
                projectdetail.setApproval_document(row.getBytes("approval_document"));
                projectdetail.setApproval_documentContentType(row.getString("approval_document_content_type"));

                projectdetail.setEdc_sif_clu_fee_paid_applicable(row.getBool("edc_sif_clu_fee_paid_applicable"));
                projectdetail.setEdc_sif_clu_fee_document(row.getBytes("edc_sif_clu_fee_document"));
                projectdetail.setEdc_sif_clu_fee_documentContentType(row.getString("edc_sif_clu_fee_document_content_type"));

                projectdetail.setPropose_employeement(row.getInt("propose_employeement"));
                return projectdetail;
            }
        ).forEach(projectdetailsList::add);
        return projectdetailsList;
    }

    public Projectdetail findOne(UUID id) {
        return mapper.get(id);
    }

    public Projectdetail save(Projectdetail projectdetail) {
        if (projectdetail.getId() == null) {
            projectdetail.setId(UUID.randomUUID());
        }
        mapper.save(projectdetail);
        return projectdetail;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
