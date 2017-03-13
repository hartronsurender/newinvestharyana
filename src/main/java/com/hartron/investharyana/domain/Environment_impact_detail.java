package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A Environment_impact_detail.
 */

@Table(name = "environment_impact_detail")
public class Environment_impact_detail implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID projectid;

    private UUID water_supply_source;

    private Integer water_usage_process;

    private Integer water_usage_cooling;

    private Integer water_usage_domestic;

    private String water_usage_other;

    private Integer water_waste_process;

    private Integer water_waste_cooling;

    private Integer water_waste_domesting;

    private String water_waste_other;

    private String waste_water_treatment;

    private ByteBuffer waste_water_treatment_document;

    @Column(name = "waste_water_treatment_document_content_type")
    private String waste_water_treatment_documentContentType;

    private UUID mode_of_disposal;

    private UUID emissionid;

    private UUID water_waste_id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProjectid() {
        return projectid;
    }

    public Environment_impact_detail projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public UUID getWater_supply_source() {
        return water_supply_source;
    }

    public Environment_impact_detail water_supply_source(UUID water_supply_source) {
        this.water_supply_source = water_supply_source;
        return this;
    }

    public void setWater_supply_source(UUID water_supply_source) {
        this.water_supply_source = water_supply_source;
    }

    public Integer getWater_usage_process() {
        return water_usage_process;
    }

    public Environment_impact_detail water_usage_process(Integer water_usage_process) {
        this.water_usage_process = water_usage_process;
        return this;
    }

    public void setWater_usage_process(Integer water_usage_process) {
        this.water_usage_process = water_usage_process;
    }

    public Integer getWater_usage_cooling() {
        return water_usage_cooling;
    }

    public Environment_impact_detail water_usage_cooling(Integer water_usage_cooling) {
        this.water_usage_cooling = water_usage_cooling;
        return this;
    }

    public void setWater_usage_cooling(Integer water_usage_cooling) {
        this.water_usage_cooling = water_usage_cooling;
    }

    public Integer getWater_usage_domestic() {
        return water_usage_domestic;
    }

    public Environment_impact_detail water_usage_domestic(Integer water_usage_domestic) {
        this.water_usage_domestic = water_usage_domestic;
        return this;
    }

    public void setWater_usage_domestic(Integer water_usage_domestic) {
        this.water_usage_domestic = water_usage_domestic;
    }

    public String getWater_usage_other() {
        return water_usage_other;
    }

    public Environment_impact_detail water_usage_other(String water_usage_other) {
        this.water_usage_other = water_usage_other;
        return this;
    }

    public void setWater_usage_other(String water_usage_other) {
        this.water_usage_other = water_usage_other;
    }

    public Integer getWater_waste_process() {
        return water_waste_process;
    }

    public Environment_impact_detail water_waste_process(Integer water_waste_process) {
        this.water_waste_process = water_waste_process;
        return this;
    }

    public void setWater_waste_process(Integer water_waste_process) {
        this.water_waste_process = water_waste_process;
    }

    public Integer getWater_waste_cooling() {
        return water_waste_cooling;
    }

    public Environment_impact_detail water_waste_cooling(Integer water_waste_cooling) {
        this.water_waste_cooling = water_waste_cooling;
        return this;
    }

    public void setWater_waste_cooling(Integer water_waste_cooling) {
        this.water_waste_cooling = water_waste_cooling;
    }

    public Integer getWater_waste_domesting() {
        return water_waste_domesting;
    }

    public Environment_impact_detail water_waste_domesting(Integer water_waste_domesting) {
        this.water_waste_domesting = water_waste_domesting;
        return this;
    }

    public void setWater_waste_domesting(Integer water_waste_domesting) {
        this.water_waste_domesting = water_waste_domesting;
    }

    public String getWater_waste_other() {
        return water_waste_other;
    }

    public Environment_impact_detail water_waste_other(String water_waste_other) {
        this.water_waste_other = water_waste_other;
        return this;
    }

    public void setWater_waste_other(String water_waste_other) {
        this.water_waste_other = water_waste_other;
    }

    public String getWaste_water_treatment() {
        return waste_water_treatment;
    }

    public Environment_impact_detail waste_water_treatment(String waste_water_treatment) {
        this.waste_water_treatment = waste_water_treatment;
        return this;
    }

    public void setWaste_water_treatment(String waste_water_treatment) {
        this.waste_water_treatment = waste_water_treatment;
    }

    public ByteBuffer getWaste_water_treatment_document() {
        return waste_water_treatment_document;
    }

    public Environment_impact_detail waste_water_treatment_document(ByteBuffer waste_water_treatment_document) {
        this.waste_water_treatment_document = waste_water_treatment_document;
        return this;
    }

    public void setWaste_water_treatment_document(ByteBuffer waste_water_treatment_document) {
        this.waste_water_treatment_document = waste_water_treatment_document;
    }

    public String getWaste_water_treatment_documentContentType() {
        return waste_water_treatment_documentContentType;
    }

    public Environment_impact_detail waste_water_treatment_documentContentType(String waste_water_treatment_documentContentType) {
        this.waste_water_treatment_documentContentType = waste_water_treatment_documentContentType;
        return this;
    }

    public void setWaste_water_treatment_documentContentType(String waste_water_treatment_documentContentType) {
        this.waste_water_treatment_documentContentType = waste_water_treatment_documentContentType;
    }

    public UUID getMode_of_disposal() {
        return mode_of_disposal;
    }

    public Environment_impact_detail mode_of_disposal(UUID mode_of_disposal) {
        this.mode_of_disposal = mode_of_disposal;
        return this;
    }

    public void setMode_of_disposal(UUID mode_of_disposal) {
        this.mode_of_disposal = mode_of_disposal;
    }

    public UUID getEmissionid() {
        return emissionid;
    }

    public Environment_impact_detail emissionid(UUID emissionid) {
        this.emissionid = emissionid;
        return this;
    }

    public void setEmissionid(UUID emissionid) {
        this.emissionid = emissionid;
    }

    public UUID getWater_waste_id() {
        return water_waste_id;
    }

    public Environment_impact_detail water_waste_id(UUID water_waste_id) {
        this.water_waste_id = water_waste_id;
        return this;
    }

    public void setWater_waste_id(UUID water_waste_id) {
        this.water_waste_id = water_waste_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Environment_impact_detail environment_impact_detail = (Environment_impact_detail) o;
        if (environment_impact_detail.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, environment_impact_detail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Environment_impact_detail{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", water_supply_source='" + water_supply_source + "'" +
            ", water_usage_process='" + water_usage_process + "'" +
            ", water_usage_cooling='" + water_usage_cooling + "'" +
            ", water_usage_domestic='" + water_usage_domestic + "'" +
            ", water_usage_other='" + water_usage_other + "'" +
            ", water_waste_process='" + water_waste_process + "'" +
            ", water_waste_cooling='" + water_waste_cooling + "'" +
            ", water_waste_domesting='" + water_waste_domesting + "'" +
            ", water_waste_other='" + water_waste_other + "'" +
            ", waste_water_treatment='" + waste_water_treatment + "'" +
            ", waste_water_treatment_document='" + waste_water_treatment_document + "'" +
            ", waste_water_treatment_documentContentType='" + waste_water_treatment_documentContentType + "'" +
            ", mode_of_disposal='" + mode_of_disposal + "'" +
            ", emissionid='" + emissionid + "'" +
            ", water_waste_id='" + water_waste_id + "'" +
            '}';
    }
}
