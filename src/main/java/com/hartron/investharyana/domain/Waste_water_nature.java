package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Waste_water_nature.
 */

@Table(name = "waste_water_nature")
public class Waste_water_nature implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String waste_water_nature_type;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getWaste_water_nature_type() {
        return waste_water_nature_type;
    }

    public Waste_water_nature waste_water_nature_type(String waste_water_nature_type) {
        this.waste_water_nature_type = waste_water_nature_type;
        return this;
    }

    public void setWaste_water_nature_type(String waste_water_nature_type) {
        this.waste_water_nature_type = waste_water_nature_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Waste_water_nature waste_water_nature = (Waste_water_nature) o;
        if (waste_water_nature.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, waste_water_nature.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Waste_water_nature{" +
            "id=" + id +
            ", waste_water_nature_type='" + waste_water_nature_type + "'" +
            '}';
    }
}
