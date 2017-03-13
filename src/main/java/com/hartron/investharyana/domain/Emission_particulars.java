package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Emission_particulars.
 */

@Table(name = "emission_particulars")
public class Emission_particulars implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String particular_type;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getParticular_type() {
        return particular_type;
    }

    public Emission_particulars particular_type(String particular_type) {
        this.particular_type = particular_type;
        return this;
    }

    public void setParticular_type(String particular_type) {
        this.particular_type = particular_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Emission_particulars emission_particulars = (Emission_particulars) o;
        if (emission_particulars.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, emission_particulars.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Emission_particulars{" +
            "id=" + id +
            ", particular_type='" + particular_type + "'" +
            '}';
    }
}
