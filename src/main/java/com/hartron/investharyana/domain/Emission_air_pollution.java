package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Emission_air_pollution.
 */

@Table(name = "emission_air_pollution")
public class Emission_air_pollution implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String pollution_controll_device;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPollution_controll_device() {
        return pollution_controll_device;
    }

    public Emission_air_pollution pollution_controll_device(String pollution_controll_device) {
        this.pollution_controll_device = pollution_controll_device;
        return this;
    }

    public void setPollution_controll_device(String pollution_controll_device) {
        this.pollution_controll_device = pollution_controll_device;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Emission_air_pollution emission_air_pollution = (Emission_air_pollution) o;
        if (emission_air_pollution.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, emission_air_pollution.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Emission_air_pollution{" +
            "id=" + id +
            ", pollution_controll_device='" + pollution_controll_device + "'" +
            '}';
    }
}
