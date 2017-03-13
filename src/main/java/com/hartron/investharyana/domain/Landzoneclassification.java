package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Landzoneclassification.
 */

@Table(name = "landzoneclassification")
public class Landzoneclassification implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String land_zone_classification;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLand_zone_classification() {
        return land_zone_classification;
    }

    public Landzoneclassification land_zone_classification(String land_zone_classification) {
        this.land_zone_classification = land_zone_classification;
        return this;
    }

    public void setLand_zone_classification(String land_zone_classification) {
        this.land_zone_classification = land_zone_classification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Landzoneclassification landzoneclassification = (Landzoneclassification) o;
        if (landzoneclassification.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, landzoneclassification.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Landzoneclassification{" +
            "id=" + id +
            ", land_zone_classification='" + land_zone_classification + "'" +
            '}';
    }
}
