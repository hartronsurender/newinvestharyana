package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Manufactur_unit.
 */

@Table(name = "manufactur_unit")
public class Manufactur_unit implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String unit_type;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUnit_type() {
        return unit_type;
    }

    public Manufactur_unit unit_type(String unit_type) {
        this.unit_type = unit_type;
        return this;
    }

    public void setUnit_type(String unit_type) {
        this.unit_type = unit_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Manufactur_unit manufactur_unit = (Manufactur_unit) o;
        if (manufactur_unit.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, manufactur_unit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Manufactur_unit{" +
            "id=" + id +
            ", unit_type='" + unit_type + "'" +
            '}';
    }
}
