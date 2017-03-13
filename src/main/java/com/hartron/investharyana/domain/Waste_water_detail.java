package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Waste_water_detail.
 */

@Table(name = "waste_water_detail")
public class Waste_water_detail implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID projectid;

    private String source_of_generation;

    private UUID nature_type;

    private String quantity;

    private UUID mode_of_disposal;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProjectid() {
        return projectid;
    }

    public Waste_water_detail projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public String getSource_of_generation() {
        return source_of_generation;
    }

    public Waste_water_detail source_of_generation(String source_of_generation) {
        this.source_of_generation = source_of_generation;
        return this;
    }

    public void setSource_of_generation(String source_of_generation) {
        this.source_of_generation = source_of_generation;
    }

    public UUID getNature_type() {
        return nature_type;
    }

    public Waste_water_detail nature_type(UUID nature_type) {
        this.nature_type = nature_type;
        return this;
    }

    public void setNature_type(UUID nature_type) {
        this.nature_type = nature_type;
    }

    public String getQuantity() {
        return quantity;
    }

    public Waste_water_detail quantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public UUID getMode_of_disposal() {
        return mode_of_disposal;
    }

    public Waste_water_detail mode_of_disposal(UUID mode_of_disposal) {
        this.mode_of_disposal = mode_of_disposal;
        return this;
    }

    public void setMode_of_disposal(UUID mode_of_disposal) {
        this.mode_of_disposal = mode_of_disposal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Waste_water_detail waste_water_detail = (Waste_water_detail) o;
        if (waste_water_detail.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, waste_water_detail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Waste_water_detail{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", source_of_generation='" + source_of_generation + "'" +
            ", nature_type='" + nature_type + "'" +
            ", quantity='" + quantity + "'" +
            ", mode_of_disposal='" + mode_of_disposal + "'" +
            '}';
    }
}
