package com.hartron.investharyana.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Financeinvestment entity.
 */
public class FinanceinvestmentDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private BigDecimal land_cost;

    private BigDecimal building_cost;

    private BigDecimal plan_machinery_cost;

    private BigDecimal misc_assets;

    private BigDecimal totalproject_cost;

    private Boolean fdi_applicable;

    private UUID fdi_country;

    private BigDecimal fdi_value;

    private UUID fdi_resource;

    private ZonedDateTime project_construction_start_date;

    private ZonedDateTime project_commercial_activity_date;

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
    public BigDecimal getLand_cost() {
        return land_cost;
    }

    public void setLand_cost(BigDecimal land_cost) {
        this.land_cost = land_cost;
    }
    public BigDecimal getBuilding_cost() {
        return building_cost;
    }

    public void setBuilding_cost(BigDecimal building_cost) {
        this.building_cost = building_cost;
    }
    public BigDecimal getPlan_machinery_cost() {
        return plan_machinery_cost;
    }

    public void setPlan_machinery_cost(BigDecimal plan_machinery_cost) {
        this.plan_machinery_cost = plan_machinery_cost;
    }
    public BigDecimal getMisc_assets() {
        return misc_assets;
    }

    public void setMisc_assets(BigDecimal misc_assets) {
        this.misc_assets = misc_assets;
    }
    public BigDecimal getTotalproject_cost() {
        return totalproject_cost;
    }

    public void setTotalproject_cost(BigDecimal totalproject_cost) {
        this.totalproject_cost = totalproject_cost;
    }
    public Boolean getFdi_applicable() {
        return fdi_applicable;
    }

    public void setFdi_applicable(Boolean fdi_applicable) {
        this.fdi_applicable = fdi_applicable;
    }
    public UUID getFdi_country() {
        return fdi_country;
    }

    public void setFdi_country(UUID fdi_country) {
        this.fdi_country = fdi_country;
    }
    public BigDecimal getFdi_value() {
        return fdi_value;
    }

    public void setFdi_value(BigDecimal fdi_value) {
        this.fdi_value = fdi_value;
    }
    public UUID getFdi_resource() {
        return fdi_resource;
    }

    public void setFdi_resource(UUID fdi_resource) {
        this.fdi_resource = fdi_resource;
    }
    public ZonedDateTime getProject_construction_start_date() {
        return project_construction_start_date;
    }

    public void setProject_construction_start_date(ZonedDateTime project_construction_start_date) {
        this.project_construction_start_date = project_construction_start_date;
    }
    public ZonedDateTime getProject_commercial_activity_date() {
        return project_commercial_activity_date;
    }

    public void setProject_commercial_activity_date(ZonedDateTime project_commercial_activity_date) {
        this.project_commercial_activity_date = project_commercial_activity_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FinanceinvestmentDTO financeinvestmentDTO = (FinanceinvestmentDTO) o;

        if ( ! Objects.equals(id, financeinvestmentDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FinanceinvestmentDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", land_cost='" + land_cost + "'" +
            ", building_cost='" + building_cost + "'" +
            ", plan_machinery_cost='" + plan_machinery_cost + "'" +
            ", misc_assets='" + misc_assets + "'" +
            ", totalproject_cost='" + totalproject_cost + "'" +
            ", fdi_applicable='" + fdi_applicable + "'" +
            ", fdi_country='" + fdi_country + "'" +
            ", fdi_value='" + fdi_value + "'" +
            ", fdi_resource='" + fdi_resource + "'" +
            ", project_construction_start_date='" + project_construction_start_date + "'" +
            ", project_commercial_activity_date='" + project_commercial_activity_date + "'" +
            '}';
    }
}
