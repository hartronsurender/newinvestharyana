package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A Projectsitedetail.
 */

@Table(name = "projectsitedetail")
public class Projectsitedetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID projectid;

    private Boolean certifiedownership;

    private ByteBuffer ownership_document;

    @Column(name = "ownership_document_content_type")
    private String ownership_documentContentType;

    private Boolean leaseapplicable;

    private ByteBuffer lease_document;

    @Column(name = "lease_document_content_type")
    private String lease_documentContentType;

    private Boolean landagreementapplicable;

    private ByteBuffer landagreement_document;

    @Column(name = "landagreement_document_content_type")
    private String landagreement_documentContentType;

    private ByteBuffer siteplan_document;

    @Column(name = "siteplan_document_content_type")
    private String siteplan_documentContentType;

    private ByteBuffer locationplan_document;

    @Column(name = "locationplan_document_content_type")
    private String locationplan_documentContentType;

    private ByteBuffer linearstripplan_document;

    @Column(name = "linearstripplan_document_content_type")
    private String linearstripplan_documentContentType;

    private UUID connectingroadid;

    private Boolean intersection_distance;

    private Boolean railway_distance;

    private Boolean confirmity_landuse;

    private UUID landzone_usetype;

    private ByteBuffer sitesituated_document;

    @Column(name = "sitesituated_document_content_type")
    private String sitesituated_documentContentType;

    private UUID building_existed;

    private Boolean existing_building_applicable;

    private ByteBuffer building_plan_document;

    @Column(name = "building_plan_document_content_type")
    private String building_plan_documentContentType;

    private Boolean sitesituatedincontrolledarea;

    private ByteBuffer controlled_area_document;

    @Column(name = "controlled_area_document_content_type")
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

    public Projectsitedetail projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public Boolean isCertifiedownership() {
        return certifiedownership;
    }

    public Projectsitedetail certifiedownership(Boolean certifiedownership) {
        this.certifiedownership = certifiedownership;
        return this;
    }

    public void setCertifiedownership(Boolean certifiedownership) {
        this.certifiedownership = certifiedownership;
    }

    public ByteBuffer getOwnership_document() {
        return ownership_document;
    }

    public Projectsitedetail ownership_document(ByteBuffer ownership_document) {
        this.ownership_document = ownership_document;
        return this;
    }

    public void setOwnership_document(ByteBuffer ownership_document) {
        this.ownership_document = ownership_document;
    }

    public String getOwnership_documentContentType() {
        return ownership_documentContentType;
    }

    public Projectsitedetail ownership_documentContentType(String ownership_documentContentType) {
        this.ownership_documentContentType = ownership_documentContentType;
        return this;
    }

    public void setOwnership_documentContentType(String ownership_documentContentType) {
        this.ownership_documentContentType = ownership_documentContentType;
    }

    public Boolean isLeaseapplicable() {
        return leaseapplicable;
    }

    public Projectsitedetail leaseapplicable(Boolean leaseapplicable) {
        this.leaseapplicable = leaseapplicable;
        return this;
    }

    public void setLeaseapplicable(Boolean leaseapplicable) {
        this.leaseapplicable = leaseapplicable;
    }

    public ByteBuffer getLease_document() {
        return lease_document;
    }

    public Projectsitedetail lease_document(ByteBuffer lease_document) {
        this.lease_document = lease_document;
        return this;
    }

    public void setLease_document(ByteBuffer lease_document) {
        this.lease_document = lease_document;
    }

    public String getLease_documentContentType() {
        return lease_documentContentType;
    }

    public Projectsitedetail lease_documentContentType(String lease_documentContentType) {
        this.lease_documentContentType = lease_documentContentType;
        return this;
    }

    public void setLease_documentContentType(String lease_documentContentType) {
        this.lease_documentContentType = lease_documentContentType;
    }

    public Boolean isLandagreementapplicable() {
        return landagreementapplicable;
    }

    public Projectsitedetail landagreementapplicable(Boolean landagreementapplicable) {
        this.landagreementapplicable = landagreementapplicable;
        return this;
    }

    public void setLandagreementapplicable(Boolean landagreementapplicable) {
        this.landagreementapplicable = landagreementapplicable;
    }

    public ByteBuffer getLandagreement_document() {
        return landagreement_document;
    }

    public Projectsitedetail landagreement_document(ByteBuffer landagreement_document) {
        this.landagreement_document = landagreement_document;
        return this;
    }

    public void setLandagreement_document(ByteBuffer landagreement_document) {
        this.landagreement_document = landagreement_document;
    }

    public String getLandagreement_documentContentType() {
        return landagreement_documentContentType;
    }

    public Projectsitedetail landagreement_documentContentType(String landagreement_documentContentType) {
        this.landagreement_documentContentType = landagreement_documentContentType;
        return this;
    }

    public void setLandagreement_documentContentType(String landagreement_documentContentType) {
        this.landagreement_documentContentType = landagreement_documentContentType;
    }

    public ByteBuffer getSiteplan_document() {
        return siteplan_document;
    }

    public Projectsitedetail siteplan_document(ByteBuffer siteplan_document) {
        this.siteplan_document = siteplan_document;
        return this;
    }

    public void setSiteplan_document(ByteBuffer siteplan_document) {
        this.siteplan_document = siteplan_document;
    }

    public String getSiteplan_documentContentType() {
        return siteplan_documentContentType;
    }

    public Projectsitedetail siteplan_documentContentType(String siteplan_documentContentType) {
        this.siteplan_documentContentType = siteplan_documentContentType;
        return this;
    }

    public void setSiteplan_documentContentType(String siteplan_documentContentType) {
        this.siteplan_documentContentType = siteplan_documentContentType;
    }

    public ByteBuffer getLocationplan_document() {
        return locationplan_document;
    }

    public Projectsitedetail locationplan_document(ByteBuffer locationplan_document) {
        this.locationplan_document = locationplan_document;
        return this;
    }

    public void setLocationplan_document(ByteBuffer locationplan_document) {
        this.locationplan_document = locationplan_document;
    }

    public String getLocationplan_documentContentType() {
        return locationplan_documentContentType;
    }

    public Projectsitedetail locationplan_documentContentType(String locationplan_documentContentType) {
        this.locationplan_documentContentType = locationplan_documentContentType;
        return this;
    }

    public void setLocationplan_documentContentType(String locationplan_documentContentType) {
        this.locationplan_documentContentType = locationplan_documentContentType;
    }

    public ByteBuffer getLinearstripplan_document() {
        return linearstripplan_document;
    }

    public Projectsitedetail linearstripplan_document(ByteBuffer linearstripplan_document) {
        this.linearstripplan_document = linearstripplan_document;
        return this;
    }

    public void setLinearstripplan_document(ByteBuffer linearstripplan_document) {
        this.linearstripplan_document = linearstripplan_document;
    }

    public String getLinearstripplan_documentContentType() {
        return linearstripplan_documentContentType;
    }

    public Projectsitedetail linearstripplan_documentContentType(String linearstripplan_documentContentType) {
        this.linearstripplan_documentContentType = linearstripplan_documentContentType;
        return this;
    }

    public void setLinearstripplan_documentContentType(String linearstripplan_documentContentType) {
        this.linearstripplan_documentContentType = linearstripplan_documentContentType;
    }

    public UUID getConnectingroadid() {
        return connectingroadid;
    }

    public Projectsitedetail connectingroadid(UUID connectingroadid) {
        this.connectingroadid = connectingroadid;
        return this;
    }

    public void setConnectingroadid(UUID connectingroadid) {
        this.connectingroadid = connectingroadid;
    }

    public Boolean isIntersection_distance() {
        return intersection_distance;
    }

    public Projectsitedetail intersection_distance(Boolean intersection_distance) {
        this.intersection_distance = intersection_distance;
        return this;
    }

    public void setIntersection_distance(Boolean intersection_distance) {
        this.intersection_distance = intersection_distance;
    }

    public Boolean isRailway_distance() {
        return railway_distance;
    }

    public Projectsitedetail railway_distance(Boolean railway_distance) {
        this.railway_distance = railway_distance;
        return this;
    }

    public void setRailway_distance(Boolean railway_distance) {
        this.railway_distance = railway_distance;
    }

    public Boolean isConfirmity_landuse() {
        return confirmity_landuse;
    }

    public Projectsitedetail confirmity_landuse(Boolean confirmity_landuse) {
        this.confirmity_landuse = confirmity_landuse;
        return this;
    }

    public void setConfirmity_landuse(Boolean confirmity_landuse) {
        this.confirmity_landuse = confirmity_landuse;
    }

    public UUID getLandzone_usetype() {
        return landzone_usetype;
    }

    public Projectsitedetail landzone_usetype(UUID landzone_usetype) {
        this.landzone_usetype = landzone_usetype;
        return this;
    }

    public void setLandzone_usetype(UUID landzone_usetype) {
        this.landzone_usetype = landzone_usetype;
    }

    public ByteBuffer getSitesituated_document() {
        return sitesituated_document;
    }

    public Projectsitedetail sitesituated_document(ByteBuffer sitesituated_document) {
        this.sitesituated_document = sitesituated_document;
        return this;
    }

    public void setSitesituated_document(ByteBuffer sitesituated_document) {
        this.sitesituated_document = sitesituated_document;
    }

    public String getSitesituated_documentContentType() {
        return sitesituated_documentContentType;
    }

    public Projectsitedetail sitesituated_documentContentType(String sitesituated_documentContentType) {
        this.sitesituated_documentContentType = sitesituated_documentContentType;
        return this;
    }

    public void setSitesituated_documentContentType(String sitesituated_documentContentType) {
        this.sitesituated_documentContentType = sitesituated_documentContentType;
    }

    public UUID getBuilding_existed() {
        return building_existed;
    }

    public Projectsitedetail building_existed(UUID building_existed) {
        this.building_existed = building_existed;
        return this;
    }

    public void setBuilding_existed(UUID building_existed) {
        this.building_existed = building_existed;
    }

    public Boolean isExisting_building_applicable() {
        return existing_building_applicable;
    }

    public Projectsitedetail existing_building_applicable(Boolean existing_building_applicable) {
        this.existing_building_applicable = existing_building_applicable;
        return this;
    }

    public void setExisting_building_applicable(Boolean existing_building_applicable) {
        this.existing_building_applicable = existing_building_applicable;
    }

    public ByteBuffer getBuilding_plan_document() {
        return building_plan_document;
    }

    public Projectsitedetail building_plan_document(ByteBuffer building_plan_document) {
        this.building_plan_document = building_plan_document;
        return this;
    }

    public void setBuilding_plan_document(ByteBuffer building_plan_document) {
        this.building_plan_document = building_plan_document;
    }

    public String getBuilding_plan_documentContentType() {
        return building_plan_documentContentType;
    }

    public Projectsitedetail building_plan_documentContentType(String building_plan_documentContentType) {
        this.building_plan_documentContentType = building_plan_documentContentType;
        return this;
    }

    public void setBuilding_plan_documentContentType(String building_plan_documentContentType) {
        this.building_plan_documentContentType = building_plan_documentContentType;
    }

    public Boolean isSitesituatedincontrolledarea() {
        return sitesituatedincontrolledarea;
    }

    public Projectsitedetail sitesituatedincontrolledarea(Boolean sitesituatedincontrolledarea) {
        this.sitesituatedincontrolledarea = sitesituatedincontrolledarea;
        return this;
    }

    public void setSitesituatedincontrolledarea(Boolean sitesituatedincontrolledarea) {
        this.sitesituatedincontrolledarea = sitesituatedincontrolledarea;
    }

    public ByteBuffer getControlled_area_document() {
        return controlled_area_document;
    }

    public Projectsitedetail controlled_area_document(ByteBuffer controlled_area_document) {
        this.controlled_area_document = controlled_area_document;
        return this;
    }

    public void setControlled_area_document(ByteBuffer controlled_area_document) {
        this.controlled_area_document = controlled_area_document;
    }

    public String getControlled_area_documentContentType() {
        return controlled_area_documentContentType;
    }

    public Projectsitedetail controlled_area_documentContentType(String controlled_area_documentContentType) {
        this.controlled_area_documentContentType = controlled_area_documentContentType;
        return this;
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
        Projectsitedetail projectsitedetail = (Projectsitedetail) o;
        if (projectsitedetail.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectsitedetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Projectsitedetail{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", certifiedownership='" + certifiedownership + "'" +
            ", ownership_document='" + ownership_document + "'" +
            ", ownership_documentContentType='" + ownership_documentContentType + "'" +
            ", leaseapplicable='" + leaseapplicable + "'" +
            ", lease_document='" + lease_document + "'" +
            ", lease_documentContentType='" + lease_documentContentType + "'" +
            ", landagreementapplicable='" + landagreementapplicable + "'" +
            ", landagreement_document='" + landagreement_document + "'" +
            ", landagreement_documentContentType='" + landagreement_documentContentType + "'" +
            ", siteplan_document='" + siteplan_document + "'" +
            ", siteplan_documentContentType='" + siteplan_documentContentType + "'" +
            ", locationplan_document='" + locationplan_document + "'" +
            ", locationplan_documentContentType='" + locationplan_documentContentType + "'" +
            ", linearstripplan_document='" + linearstripplan_document + "'" +
            ", linearstripplan_documentContentType='" + linearstripplan_documentContentType + "'" +
            ", connectingroadid='" + connectingroadid + "'" +
            ", intersection_distance='" + intersection_distance + "'" +
            ", railway_distance='" + railway_distance + "'" +
            ", confirmity_landuse='" + confirmity_landuse + "'" +
            ", landzone_usetype='" + landzone_usetype + "'" +
            ", sitesituated_document='" + sitesituated_document + "'" +
            ", sitesituated_documentContentType='" + sitesituated_documentContentType + "'" +
            ", building_existed='" + building_existed + "'" +
            ", existing_building_applicable='" + existing_building_applicable + "'" +
            ", building_plan_document='" + building_plan_document + "'" +
            ", building_plan_documentContentType='" + building_plan_documentContentType + "'" +
            ", sitesituatedincontrolledarea='" + sitesituatedincontrolledarea + "'" +
            ", controlled_area_document='" + controlled_area_document + "'" +
            ", controlled_area_documentContentType='" + controlled_area_documentContentType + "'" +
            '}';
    }
}
