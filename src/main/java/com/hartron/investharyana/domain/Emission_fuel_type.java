package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Emission_fuel_type.
 */

@Table(name = "emission_fuel_type")
public class Emission_fuel_type implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String fuel_type;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public Emission_fuel_type fuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
        return this;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Emission_fuel_type emission_fuel_type = (Emission_fuel_type) o;
        if (emission_fuel_type.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, emission_fuel_type.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Emission_fuel_type{" +
            "id=" + id +
            ", fuel_type='" + fuel_type + "'" +
            '}';
    }
}
