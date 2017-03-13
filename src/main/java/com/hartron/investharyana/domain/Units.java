package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Units.
 */

@Table(name = "units")
public class Units implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String unittype;

    private String description;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUnittype() {
        return unittype;
    }

    public Units unittype(String unittype) {
        this.unittype = unittype;
        return this;
    }

    public void setUnittype(String unittype) {
        this.unittype = unittype;
    }

    public String getDescription() {
        return description;
    }

    public Units description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Units units = (Units) o;
        if (units.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, units.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Units{" +
            "id=" + id +
            ", unittype='" + unittype + "'" +
            ", description='" + description + "'" +
            '}';
    }
}
