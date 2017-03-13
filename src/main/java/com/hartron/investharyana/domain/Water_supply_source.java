package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Water_supply_source.
 */

@Table(name = "water_supply_source")
public class Water_supply_source implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String source_type;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSource_type() {
        return source_type;
    }

    public Water_supply_source source_type(String source_type) {
        this.source_type = source_type;
        return this;
    }

    public void setSource_type(String source_type) {
        this.source_type = source_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Water_supply_source water_supply_source = (Water_supply_source) o;
        if (water_supply_source.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, water_supply_source.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Water_supply_source{" +
            "id=" + id +
            ", source_type='" + source_type + "'" +
            '}';
    }
}
