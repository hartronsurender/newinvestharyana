package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A Projectdetail.
 */

@Table(name = "projectdetail")
public class Projectdetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String siteaddress;

    private UUID district;

    private UUID block;

    private UUID city_town_village;

    private UUID tehsil_subtehsil;

    private Boolean multyvillageinvolved;

    private String villageinvolved;

    private Boolean falls_in_aravalli;

    private Boolean landprocured;

    private Boolean allotedbyhsiidc;

    private String estate;

    private String cluster;

    private String phase;

    private String sector;

    private String plotno;

    private String hadbastno;

    private ByteBuffer khasradocument;

    @Column(name = "khasradocument_content_type")
    private String khasradocumentContentType;

    private Boolean lies_under_mc;

    private Integer distance_from_mc;

    private Boolean located_in_urban;

    private ByteBuffer revenue_shajra_document;

    @Column(name = "revenue_shajra_document_content_type")
    private String revenue_shajra_documentContentType;

    private ByteBuffer jamabandi_document;

    @Column(name = "jamabandi_document_content_type")
    private String jamabandi_documentContentType;

    private ByteBuffer nonencumbrancecertificate;

    @Column(name = "nonencumbrancecertificate_content_type")
    private String nonencumbrancecertificateContentType;

    private BigDecimal totalproposedprojectarea;

    private BigDecimal proposed_build_up_area;

    private UUID secotr;

    private UUID projectpurpose;

    private UUID size_of_industry;

    private UUID project_type;

    private String nic_code;

    private UUID category_of_project;

    private UUID collaboration_with_foreign;

    private ByteBuffer detail_project_report;

    @Column(name = "detail_project_report_content_type")
    private String detail_project_reportContentType;

    private Boolean existing_regulatory_approval;

    private UUID approval_application_form;

    private ByteBuffer approval_document;

    @Column(name = "approval_document_content_type")
    private String approval_documentContentType;

    private Boolean edc_sif_clu_fee_paid_applicable;

    private ByteBuffer edc_sif_clu_fee_document;

    @Column(name = "edc_sif_clu_fee_document_content_type")
    private String edc_sif_clu_fee_documentContentType;

    private Integer propose_employeement;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSiteaddress() {
        return siteaddress;
    }

    public Projectdetail siteaddress(String siteaddress) {
        this.siteaddress = siteaddress;
        return this;
    }

    public void setSiteaddress(String siteaddress) {
        this.siteaddress = siteaddress;
    }

    public UUID getDistrict() {
        return district;
    }

    public Projectdetail district(UUID district) {
        this.district = district;
        return this;
    }

    public void setDistrict(UUID district) {
        this.district = district;
    }

    public UUID getBlock() {
        return block;
    }

    public Projectdetail block(UUID block) {
        this.block = block;
        return this;
    }

    public void setBlock(UUID block) {
        this.block = block;
    }

    public UUID getCity_town_village() {
        return city_town_village;
    }

    public Projectdetail city_town_village(UUID city_town_village) {
        this.city_town_village = city_town_village;
        return this;
    }

    public void setCity_town_village(UUID city_town_village) {
        this.city_town_village = city_town_village;
    }

    public UUID getTehsil_subtehsil() {
        return tehsil_subtehsil;
    }

    public Projectdetail tehsil_subtehsil(UUID tehsil_subtehsil) {
        this.tehsil_subtehsil = tehsil_subtehsil;
        return this;
    }

    public void setTehsil_subtehsil(UUID tehsil_subtehsil) {
        this.tehsil_subtehsil = tehsil_subtehsil;
    }

    public Boolean isMultyvillageinvolved() {
        return multyvillageinvolved;
    }

    public Projectdetail multyvillageinvolved(Boolean multyvillageinvolved) {
        this.multyvillageinvolved = multyvillageinvolved;
        return this;
    }

    public void setMultyvillageinvolved(Boolean multyvillageinvolved) {
        this.multyvillageinvolved = multyvillageinvolved;
    }

    public String getVillageinvolved() {
        return villageinvolved;
    }

    public Projectdetail villageinvolved(String villageinvolved) {
        this.villageinvolved = villageinvolved;
        return this;
    }

    public void setVillageinvolved(String villageinvolved) {
        this.villageinvolved = villageinvolved;
    }

    public Boolean isFalls_in_aravalli() {
        return falls_in_aravalli;
    }

    public Projectdetail falls_in_aravalli(Boolean falls_in_aravalli) {
        this.falls_in_aravalli = falls_in_aravalli;
        return this;
    }

    public void setFalls_in_aravalli(Boolean falls_in_aravalli) {
        this.falls_in_aravalli = falls_in_aravalli;
    }

    public Boolean isLandprocured() {
        return landprocured;
    }

    public Projectdetail landprocured(Boolean landprocured) {
        this.landprocured = landprocured;
        return this;
    }

    public void setLandprocured(Boolean landprocured) {
        this.landprocured = landprocured;
    }

    public Boolean isAllotedbyhsiidc() {
        return allotedbyhsiidc;
    }

    public Projectdetail allotedbyhsiidc(Boolean allotedbyhsiidc) {
        this.allotedbyhsiidc = allotedbyhsiidc;
        return this;
    }

    public void setAllotedbyhsiidc(Boolean allotedbyhsiidc) {
        this.allotedbyhsiidc = allotedbyhsiidc;
    }

    public String getEstate() {
        return estate;
    }

    public Projectdetail estate(String estate) {
        this.estate = estate;
        return this;
    }

    public void setEstate(String estate) {
        this.estate = estate;
    }

    public String getCluster() {
        return cluster;
    }

    public Projectdetail cluster(String cluster) {
        this.cluster = cluster;
        return this;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getPhase() {
        return phase;
    }

    public Projectdetail phase(String phase) {
        this.phase = phase;
        return this;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getSector() {
        return sector;
    }

    public Projectdetail sector(String sector) {
        this.sector = sector;
        return this;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getPlotno() {
        return plotno;
    }

    public Projectdetail plotno(String plotno) {
        this.plotno = plotno;
        return this;
    }

    public void setPlotno(String plotno) {
        this.plotno = plotno;
    }

    public String getHadbastno() {
        return hadbastno;
    }

    public Projectdetail hadbastno(String hadbastno) {
        this.hadbastno = hadbastno;
        return this;
    }

    public void setHadbastno(String hadbastno) {
        this.hadbastno = hadbastno;
    }

    public ByteBuffer getKhasradocument() {
        return khasradocument;
    }

    public Projectdetail khasradocument(ByteBuffer khasradocument) {
        this.khasradocument = khasradocument;
        return this;
    }

    public void setKhasradocument(ByteBuffer khasradocument) {
        this.khasradocument = khasradocument;
    }

    public String getKhasradocumentContentType() {
        return khasradocumentContentType;
    }

    public Projectdetail khasradocumentContentType(String khasradocumentContentType) {
        this.khasradocumentContentType = khasradocumentContentType;
        return this;
    }

    public void setKhasradocumentContentType(String khasradocumentContentType) {
        this.khasradocumentContentType = khasradocumentContentType;
    }

    public Boolean isLies_under_mc() {
        return lies_under_mc;
    }

    public Projectdetail lies_under_mc(Boolean lies_under_mc) {
        this.lies_under_mc = lies_under_mc;
        return this;
    }

    public void setLies_under_mc(Boolean lies_under_mc) {
        this.lies_under_mc = lies_under_mc;
    }

    public Integer getDistance_from_mc() {
        return distance_from_mc;
    }

    public Projectdetail distance_from_mc(Integer distance_from_mc) {
        this.distance_from_mc = distance_from_mc;
        return this;
    }

    public void setDistance_from_mc(Integer distance_from_mc) {
        this.distance_from_mc = distance_from_mc;
    }

    public Boolean isLocated_in_urban() {
        return located_in_urban;
    }

    public Projectdetail located_in_urban(Boolean located_in_urban) {
        this.located_in_urban = located_in_urban;
        return this;
    }

    public void setLocated_in_urban(Boolean located_in_urban) {
        this.located_in_urban = located_in_urban;
    }

    public ByteBuffer getRevenue_shajra_document() {
        return revenue_shajra_document;
    }

    public Projectdetail revenue_shajra_document(ByteBuffer revenue_shajra_document) {
        this.revenue_shajra_document = revenue_shajra_document;
        return this;
    }

    public void setRevenue_shajra_document(ByteBuffer revenue_shajra_document) {
        this.revenue_shajra_document = revenue_shajra_document;
    }

    public String getRevenue_shajra_documentContentType() {
        return revenue_shajra_documentContentType;
    }

    public Projectdetail revenue_shajra_documentContentType(String revenue_shajra_documentContentType) {
        this.revenue_shajra_documentContentType = revenue_shajra_documentContentType;
        return this;
    }

    public void setRevenue_shajra_documentContentType(String revenue_shajra_documentContentType) {
        this.revenue_shajra_documentContentType = revenue_shajra_documentContentType;
    }

    public ByteBuffer getJamabandi_document() {
        return jamabandi_document;
    }

    public Projectdetail jamabandi_document(ByteBuffer jamabandi_document) {
        this.jamabandi_document = jamabandi_document;
        return this;
    }

    public void setJamabandi_document(ByteBuffer jamabandi_document) {
        this.jamabandi_document = jamabandi_document;
    }

    public String getJamabandi_documentContentType() {
        return jamabandi_documentContentType;
    }

    public Projectdetail jamabandi_documentContentType(String jamabandi_documentContentType) {
        this.jamabandi_documentContentType = jamabandi_documentContentType;
        return this;
    }

    public void setJamabandi_documentContentType(String jamabandi_documentContentType) {
        this.jamabandi_documentContentType = jamabandi_documentContentType;
    }

    public ByteBuffer getNonencumbrancecertificate() {
        return nonencumbrancecertificate;
    }

    public Projectdetail nonencumbrancecertificate(ByteBuffer nonencumbrancecertificate) {
        this.nonencumbrancecertificate = nonencumbrancecertificate;
        return this;
    }

    public void setNonencumbrancecertificate(ByteBuffer nonencumbrancecertificate) {
        this.nonencumbrancecertificate = nonencumbrancecertificate;
    }

    public String getNonencumbrancecertificateContentType() {
        return nonencumbrancecertificateContentType;
    }

    public Projectdetail nonencumbrancecertificateContentType(String nonencumbrancecertificateContentType) {
        this.nonencumbrancecertificateContentType = nonencumbrancecertificateContentType;
        return this;
    }

    public void setNonencumbrancecertificateContentType(String nonencumbrancecertificateContentType) {
        this.nonencumbrancecertificateContentType = nonencumbrancecertificateContentType;
    }

    public BigDecimal getTotalproposedprojectarea() {
        return totalproposedprojectarea;
    }

    public Projectdetail totalproposedprojectarea(BigDecimal totalproposedprojectarea) {
        this.totalproposedprojectarea = totalproposedprojectarea;
        return this;
    }

    public void setTotalproposedprojectarea(BigDecimal totalproposedprojectarea) {
        this.totalproposedprojectarea = totalproposedprojectarea;
    }

    public BigDecimal getProposed_build_up_area() {
        return proposed_build_up_area;
    }

    public Projectdetail proposed_build_up_area(BigDecimal proposed_build_up_area) {
        this.proposed_build_up_area = proposed_build_up_area;
        return this;
    }

    public void setProposed_build_up_area(BigDecimal proposed_build_up_area) {
        this.proposed_build_up_area = proposed_build_up_area;
    }

    public UUID getSecotr() {
        return secotr;
    }

    public Projectdetail secotr(UUID secotr) {
        this.secotr = secotr;
        return this;
    }

    public void setSecotr(UUID secotr) {
        this.secotr = secotr;
    }

    public UUID getProjectpurpose() {
        return projectpurpose;
    }

    public Projectdetail projectpurpose(UUID projectpurpose) {
        this.projectpurpose = projectpurpose;
        return this;
    }

    public void setProjectpurpose(UUID projectpurpose) {
        this.projectpurpose = projectpurpose;
    }

    public UUID getSize_of_industry() {
        return size_of_industry;
    }

    public Projectdetail size_of_industry(UUID size_of_industry) {
        this.size_of_industry = size_of_industry;
        return this;
    }

    public void setSize_of_industry(UUID size_of_industry) {
        this.size_of_industry = size_of_industry;
    }

    public UUID getProject_type() {
        return project_type;
    }

    public Projectdetail project_type(UUID project_type) {
        this.project_type = project_type;
        return this;
    }

    public void setProject_type(UUID project_type) {
        this.project_type = project_type;
    }

    public String getNic_code() {
        return nic_code;
    }

    public Projectdetail nic_code(String nic_code) {
        this.nic_code = nic_code;
        return this;
    }

    public void setNic_code(String nic_code) {
        this.nic_code = nic_code;
    }

    public UUID getCategory_of_project() {
        return category_of_project;
    }

    public Projectdetail category_of_project(UUID category_of_project) {
        this.category_of_project = category_of_project;
        return this;
    }

    public void setCategory_of_project(UUID category_of_project) {
        this.category_of_project = category_of_project;
    }

    public UUID getCollaboration_with_foreign() {
        return collaboration_with_foreign;
    }

    public Projectdetail collaboration_with_foreign(UUID collaboration_with_foreign) {
        this.collaboration_with_foreign = collaboration_with_foreign;
        return this;
    }

    public void setCollaboration_with_foreign(UUID collaboration_with_foreign) {
        this.collaboration_with_foreign = collaboration_with_foreign;
    }

    public ByteBuffer getDetail_project_report() {
        return detail_project_report;
    }

    public Projectdetail detail_project_report(ByteBuffer detail_project_report) {
        this.detail_project_report = detail_project_report;
        return this;
    }

    public void setDetail_project_report(ByteBuffer detail_project_report) {
        this.detail_project_report = detail_project_report;
    }

    public String getDetail_project_reportContentType() {
        return detail_project_reportContentType;
    }

    public Projectdetail detail_project_reportContentType(String detail_project_reportContentType) {
        this.detail_project_reportContentType = detail_project_reportContentType;
        return this;
    }

    public void setDetail_project_reportContentType(String detail_project_reportContentType) {
        this.detail_project_reportContentType = detail_project_reportContentType;
    }

    public Boolean isExisting_regulatory_approval() {
        return existing_regulatory_approval;
    }

    public Projectdetail existing_regulatory_approval(Boolean existing_regulatory_approval) {
        this.existing_regulatory_approval = existing_regulatory_approval;
        return this;
    }

    public void setExisting_regulatory_approval(Boolean existing_regulatory_approval) {
        this.existing_regulatory_approval = existing_regulatory_approval;
    }

    public UUID getApproval_application_form() {
        return approval_application_form;
    }

    public Projectdetail approval_application_form(UUID approval_application_form) {
        this.approval_application_form = approval_application_form;
        return this;
    }

    public void setApproval_application_form(UUID approval_application_form) {
        this.approval_application_form = approval_application_form;
    }

    public ByteBuffer getApproval_document() {
        return approval_document;
    }

    public Projectdetail approval_document(ByteBuffer approval_document) {
        this.approval_document = approval_document;
        return this;
    }

    public void setApproval_document(ByteBuffer approval_document) {
        this.approval_document = approval_document;
    }

    public String getApproval_documentContentType() {
        return approval_documentContentType;
    }

    public Projectdetail approval_documentContentType(String approval_documentContentType) {
        this.approval_documentContentType = approval_documentContentType;
        return this;
    }

    public void setApproval_documentContentType(String approval_documentContentType) {
        this.approval_documentContentType = approval_documentContentType;
    }

    public Boolean isEdc_sif_clu_fee_paid_applicable() {
        return edc_sif_clu_fee_paid_applicable;
    }

    public Projectdetail edc_sif_clu_fee_paid_applicable(Boolean edc_sif_clu_fee_paid_applicable) {
        this.edc_sif_clu_fee_paid_applicable = edc_sif_clu_fee_paid_applicable;
        return this;
    }

    public void setEdc_sif_clu_fee_paid_applicable(Boolean edc_sif_clu_fee_paid_applicable) {
        this.edc_sif_clu_fee_paid_applicable = edc_sif_clu_fee_paid_applicable;
    }

    public ByteBuffer getEdc_sif_clu_fee_document() {
        return edc_sif_clu_fee_document;
    }

    public Projectdetail edc_sif_clu_fee_document(ByteBuffer edc_sif_clu_fee_document) {
        this.edc_sif_clu_fee_document = edc_sif_clu_fee_document;
        return this;
    }

    public void setEdc_sif_clu_fee_document(ByteBuffer edc_sif_clu_fee_document) {
        this.edc_sif_clu_fee_document = edc_sif_clu_fee_document;
    }

    public String getEdc_sif_clu_fee_documentContentType() {
        return edc_sif_clu_fee_documentContentType;
    }

    public Projectdetail edc_sif_clu_fee_documentContentType(String edc_sif_clu_fee_documentContentType) {
        this.edc_sif_clu_fee_documentContentType = edc_sif_clu_fee_documentContentType;
        return this;
    }

    public void setEdc_sif_clu_fee_documentContentType(String edc_sif_clu_fee_documentContentType) {
        this.edc_sif_clu_fee_documentContentType = edc_sif_clu_fee_documentContentType;
    }

    public Integer getPropose_employeement() {
        return propose_employeement;
    }

    public Projectdetail propose_employeement(Integer propose_employeement) {
        this.propose_employeement = propose_employeement;
        return this;
    }

    public void setPropose_employeement(Integer propose_employeement) {
        this.propose_employeement = propose_employeement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Projectdetail projectdetail = (Projectdetail) o;
        if (projectdetail.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectdetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Projectdetail{" +
            "id=" + id +
            ", siteaddress='" + siteaddress + "'" +
            ", district='" + district + "'" +
            ", block='" + block + "'" +
            ", city_town_village='" + city_town_village + "'" +
            ", tehsil_subtehsil='" + tehsil_subtehsil + "'" +
            ", multyvillageinvolved='" + multyvillageinvolved + "'" +
            ", villageinvolved='" + villageinvolved + "'" +
            ", falls_in_aravalli='" + falls_in_aravalli + "'" +
            ", landprocured='" + landprocured + "'" +
            ", allotedbyhsiidc='" + allotedbyhsiidc + "'" +
            ", estate='" + estate + "'" +
            ", cluster='" + cluster + "'" +
            ", phase='" + phase + "'" +
            ", sector='" + sector + "'" +
            ", plotno='" + plotno + "'" +
            ", hadbastno='" + hadbastno + "'" +
            ", khasradocument='" + khasradocument + "'" +
            ", khasradocumentContentType='" + khasradocumentContentType + "'" +
            ", lies_under_mc='" + lies_under_mc + "'" +
            ", distance_from_mc='" + distance_from_mc + "'" +
            ", located_in_urban='" + located_in_urban + "'" +
            ", revenue_shajra_document='" + revenue_shajra_document + "'" +
            ", revenue_shajra_documentContentType='" + revenue_shajra_documentContentType + "'" +
            ", jamabandi_document='" + jamabandi_document + "'" +
            ", jamabandi_documentContentType='" + jamabandi_documentContentType + "'" +
            ", nonencumbrancecertificate='" + nonencumbrancecertificate + "'" +
            ", nonencumbrancecertificateContentType='" + nonencumbrancecertificateContentType + "'" +
            ", totalproposedprojectarea='" + totalproposedprojectarea + "'" +
            ", proposed_build_up_area='" + proposed_build_up_area + "'" +
            ", secotr='" + secotr + "'" +
            ", projectpurpose='" + projectpurpose + "'" +
            ", size_of_industry='" + size_of_industry + "'" +
            ", project_type='" + project_type + "'" +
            ", nic_code='" + nic_code + "'" +
            ", category_of_project='" + category_of_project + "'" +
            ", collaboration_with_foreign='" + collaboration_with_foreign + "'" +
            ", detail_project_report='" + detail_project_report + "'" +
            ", detail_project_reportContentType='" + detail_project_reportContentType + "'" +
            ", existing_regulatory_approval='" + existing_regulatory_approval + "'" +
            ", approval_application_form='" + approval_application_form + "'" +
            ", approval_document='" + approval_document + "'" +
            ", approval_documentContentType='" + approval_documentContentType + "'" +
            ", edc_sif_clu_fee_paid_applicable='" + edc_sif_clu_fee_paid_applicable + "'" +
            ", edc_sif_clu_fee_document='" + edc_sif_clu_fee_document + "'" +
            ", edc_sif_clu_fee_documentContentType='" + edc_sif_clu_fee_documentContentType + "'" +
            ", propose_employeement='" + propose_employeement + "'" +
            '}';
    }
}
