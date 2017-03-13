package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Landzoneclassification entity.
 */
public class LandzoneclassificationDTO implements Serializable {

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

        LandzoneclassificationDTO landzoneclassificationDTO = (LandzoneclassificationDTO) o;

        if ( ! Objects.equals(id, landzoneclassificationDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LandzoneclassificationDTO{" +
            "id=" + id +
            ", land_zone_classification='" + land_zone_classification + "'" +
            '}';
    }
}
