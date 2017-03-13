package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projectsitedetail entity.
 */
public class ProjectsitedetailDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private Boolean certifiedownership;

    private ByteBuffer ownership_document;
    private String ownership_documentContentType;

    private Boolean leaseapplicable;

    private ByteBuffer lease_document;
    private String lease_documentContentType;

    private Boolean landagreementapplicable;

    private ByteBuffer landagreement_document;
    private String landagreement_documentContentType;

    private ByteBuffer siteplan_document;
    private String siteplan_documentContentType;

    private ByteBuffer locationplan_document;
    private String locationplan_documentContentType;

    private ByteBuffer linearstripplan_document;
    private String linearstripplan_documentContentType;

    private UUID connectingroadid;

    private Boolean intersection_distance;

    private Boolean railway_distance;

    private Boolean confirmity_landuse;

    private UUID landzone_usetype;

    private ByteBuffer sitesituated_document;
    private String sitesituated_documentContentType;

    private UUID building_existed;

    private Boolean existing_building_applicable;

    private ByteBuffer building_plan_document;
    private String building_plan_documentContentType;

    private Boolean sitesituatedincontrolledarea;

    private ByteBuffer controlled_area_document;
    private String controlled_area_documentContentType;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getProjectid() {
        return projectid;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }
    public Boolean getCertifiedownership() {
        return certifiedownership;
    }

    public void setCertifiedownership(Boolean certifiedownership) {
        this.certifiedownership = certifiedownership;
    }
    public ByteBuffer getOwnership_document() {
        return ownership_document;
    }

    public void setOwnership_document(ByteBuffer ownership_document) {
        this.ownership_document = ownership_document;
    }

    public String getOwnership_documentContentType() {
        return ownership_documentContentType;
    }

    public void setOwnership_documentContentType(String ownership_documentContentType) {
        this.ownership_documentContentType = ownership_documentContentType;
    }
    public Boolean getLeaseapplicable() {
        return leaseapplicable;
    }

    public void setLeaseapplicable(Boolean leaseapplicable) {
        this.leaseapplicable = leaseapplicable;
    }
    public ByteBuffer getLease_document() {
        return lease_document;
    }

    public void setLease_document(ByteBuffer lease_document) {
        this.lease_document = lease_document;
    }

    public String getLease_documentContentType() {
        return lease_documentContentType;
    }

    public void setLease_documentContentType(String lease_documentContentType) {
        this.lease_documentContentType = lease_documentContentType;
    }
    public Boolean getLandagreementapplicable() {
        return landagreementapplicable;
    }

    public void setLandagreementapplicable(Boolean landagreementapplicable) {
        this.landagreementapplicable = landagreementapplicable;
    }
    public ByteBuffer getLandagreement_document() {
        return landagreement_document;
    }

    public void setLandagreement_document(ByteBuffer landagreement_document) {
        this.landagreement_document = landagreement_document;
    }

    public String getLandagreement_documentContentType() {
        return landagreement_documentContentType;
    }

    public void setLandagreement_documentContentType(String landagreement_documentContentType) {
        this.landagreement_documentContentType = landagreement_documentContentType;
    }
    public ByteBuffer getSiteplan_document() {
        return siteplan_document;
    }

    public void setSiteplan_document(ByteBuffer siteplan_document) {
        this.siteplan_document = siteplan_document;
    }

    public String getSiteplan_documentContentType() {
        return siteplan_documentContentType;
    }

    public void setSiteplan_documentContentType(String siteplan_documentContentType) {
        this.siteplan_documentContentType = siteplan_documentContentType;
    }
    public ByteBuffer getLocationplan_document() {
        return locationplan_document;
    }

    public void setLocationplan_document(ByteBuffer locationplan_document) {
        this.locationplan_document = locationplan_document;
    }

    public String getLocationplan_documentContentType() {
        return locationplan_documentContentType;
    }

    public void setLocationplan_documentContentType(String locationplan_documentContentType) {
        this.locationplan_documentContentType = locationplan_documentContentType;
    }
    public ByteBuffer getLinearstripplan_document() {
        return linearstripplan_document;
    }

    public void setLinearstripplan_document(ByteBuffer linearstripplan_document) {
        this.linearstripplan_document = linearstripplan_document;
    }

    public String getLinearstripplan_documentContentType() {
        return linearstripplan_documentContentType;
    }

    public void setLinearstripplan_documentContentType(String linearstripplan_documentContentType) {
        this.linearstripplan_documentContentType = linearstripplan_documentContentType;
    }
    public UUID getConnectingroadid() {
        return connectingroadid;
    }

    public void setConnectingroadid(UUID connectingroadid) {
        this.connectingroadid = connectingroadid;
    }
    public Boolean getIntersection_distance() {
        return intersection_distance;
    }

    public void setIntersection_distance(Boolean intersection_distance) {
        this.intersection_distance = intersection_distance;
    }
    public Boolean getRailway_distance() {
        return railway_distance;
    }

    public void setRailway_distance(Boolean railway_distance) {
        this.railway_distance = railway_distance;
    }
    public Boolean getConfirmity_landuse() {
        return confirmity_landuse;
    }

    public void setConfirmity_landuse(Boolean confirmity_landuse) {
        this.confirmity_landuse = confirmity_landuse;
    }
    public UUID getLandzone_usetype() {
        return landzone_usetype;
    }

    public void setLandzone_usetype(UUID landzone_usetype) {
        this.landzone_usetype = landzone_usetype;
    }
    public ByteBuffer getSitesituated_document() {
        return sitesituated_document;
    }

    public void setSitesituated_document(ByteBuffer sitesituated_document) {
        this.sitesituated_document = sitesituated_document;
    }

    public String getSitesituated_documentContentType() {
        return sitesituated_documentContentType;
    }

    public void setSitesituated_documentContentType(String sitesituated_documentContentType) {
        this.sitesituated_documentContentType = sitesituated_documentContentType;
    }
    public UUID getBuilding_existed() {
        return building_existed;
    }

    public void setBuilding_existed(UUID building_existed) {
        this.building_existed = building_existed;
    }
    public Boolean getExisting_building_applicable() {
        return existing_building_applicable;
    }

    public void setExisting_building_applicable(Boolean existing_building_applicable) {
        this.existing_building_applicable = existing_building_applicable;
    }
    public ByteBuffer getBuilding_plan_document() {
        return building_plan_document;
    }

    public void setBuilding_plan_document(ByteBuffer building_plan_document) {
        this.building_plan_document = building_plan_document;
    }

    public String getBuilding_plan_documentContentType() {
        return building_plan_documentContentType;
    }

    public void setBuilding_plan_documentContentType(String building_plan_documentContentType) {
        this.building_plan_documentContentType = building_plan_documentContentType;
    }
    public Boolean getSitesituatedincontrolledarea() {
        return sitesituatedincontrolledarea;
    }

    public void setSitesituatedincontrolledarea(Boolean sitesituatedincontrolledarea) {
        this.sitesituatedincontrolledarea = sitesituatedincontrolledarea;
    }
    public ByteBuffer getControlled_area_document() {
        return controlled_area_document;
    }

    public void setControlled_area_document(ByteBuffer controlled_area_document) {
        this.controlled_area_document = controlled_area_document;
    }

    public String getControlled_area_documentContentType() {
        return controlled_area_documentContentType;
    }

    public void setControlled_area_documentContentType(String controlled_area_documentContentType) {
        this.controlled_area_documentContentType = controlled_area_documentContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectsitedetailDTO projectsitedetailDTO = (ProjectsitedetailDTO) o;

        if ( ! Objects.equals(id, projectsitedetailDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectsitedetailDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", certifiedownership='" + certifiedownership + "'" +
            ", ownership_document='" + ownership_document + "'" +
            ", leaseapplicable='" + leaseapplicable + "'" +
            ", lease_document='" + lease_document + "'" +
            ", landagreementapplicable='" + landagreementapplicable + "'" +
            ", landagreement_document='" + landagreement_document + "'" +
            ", siteplan_document='" + siteplan_document + "'" +
            ", locationplan_document='" + locationplan_document + "'" +
            ", linearstripplan_document='" + linearstripplan_document + "'" +
            ", connectingroadid='" + connectingroadid + "'" +
            ", intersection_distance='" + intersection_distance + "'" +
            ", railway_distance='" + railway_distance + "'" +
            ", confirmity_landuse='" + confirmity_landuse + "'" +
            ", landzone_usetype='" + landzone_usetype + "'" +
            ", sitesituated_document='" + sitesituated_document + "'" +
            ", building_existed='" + building_existed + "'" +
            ", existing_building_applicable='" + existing_building_applicable + "'" +
            ", building_plan_document='" + building_plan_document + "'" +
            ", sitesituatedincontrolledarea='" + sitesituatedincontrolledarea + "'" +
            ", controlled_area_document='" + controlled_area_document + "'" +
            '}';
    }
}
