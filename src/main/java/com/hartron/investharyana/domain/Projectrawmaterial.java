package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Projectrawmaterial.
 */

@Table(name = "projectrawmaterial")
public class Projectrawmaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID projectid;

    private String raw_material;

    private Integer raw_quantity;

    private UUID raw_unit;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProjectid() {
        return projectid;
    }

    public Projectrawmaterial projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public String getRaw_material() {
        return raw_material;
    }

    public Projectrawmaterial raw_material(String raw_material) {
        this.raw_material = raw_material;
        return this;
    }

    public void setRaw_material(String raw_material) {
        this.raw_material = raw_material;
    }

    public Integer getRaw_quantity() {
        return raw_quantity;
    }

    public Projectrawmaterial raw_quantity(Integer raw_quantity) {
        this.raw_quantity = raw_quantity;
        return this;
    }

    public void setRaw_quantity(Integer raw_quantity) {
        this.raw_quantity = raw_quantity;
    }

    public UUID getRaw_unit() {
        return raw_unit;
    }

    public Projectrawmaterial raw_unit(UUID raw_unit) {
        this.raw_unit = raw_unit;
        return this;
    }

    public void setRaw_unit(UUID raw_unit) {
        this.raw_unit = raw_unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Projectrawmaterial projectrawmaterial = (Projectrawmaterial) o;
        if (projectrawmaterial.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectrawmaterial.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Projectrawmaterial{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", raw_material='" + raw_material + "'" +
            ", raw_quantity='" + raw_quantity + "'" +
            ", raw_unit='" + raw_unit + "'" +
            '}';
    }
}
