package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Disposal_for_discharge.
 */

@Table(name = "disposal_for_discharge")
public class Disposal_for_discharge implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String disposal_for_discharge_type;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDisposal_for_discharge_type() {
        return disposal_for_discharge_type;
    }

    public Disposal_for_discharge disposal_for_discharge_type(String disposal_for_discharge_type) {
        this.disposal_for_discharge_type = disposal_for_discharge_type;
        return this;
    }

    public void setDisposal_for_discharge_type(String disposal_for_discharge_type) {
        this.disposal_for_discharge_type = disposal_for_discharge_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Disposal_for_discharge disposal_for_discharge = (Disposal_for_discharge) o;
        if (disposal_for_discharge.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, disposal_for_discharge.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Disposal_for_discharge{" +
            "id=" + id +
            ", disposal_for_discharge_type='" + disposal_for_discharge_type + "'" +
            '}';
    }
}
