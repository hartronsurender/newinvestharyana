package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Waste_water_disposal_mode.
 */

@Table(name = "waste_water_disposal_mode")
public class Waste_water_disposal_mode implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String disposal_mode;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDisposal_mode() {
        return disposal_mode;
    }

    public Waste_water_disposal_mode disposal_mode(String disposal_mode) {
        this.disposal_mode = disposal_mode;
        return this;
    }

    public void setDisposal_mode(String disposal_mode) {
        this.disposal_mode = disposal_mode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Waste_water_disposal_mode waste_water_disposal_mode = (Waste_water_disposal_mode) o;
        if (waste_water_disposal_mode.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, waste_water_disposal_mode.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Waste_water_disposal_mode{" +
            "id=" + id +
            ", disposal_mode='" + disposal_mode + "'" +
            '}';
    }
}
