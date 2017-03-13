package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Water_supply_source entity.
 */
public class Water_supply_sourceDTO implements Serializable {

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

        Water_supply_sourceDTO water_supply_sourceDTO = (Water_supply_sourceDTO) o;

        if ( ! Objects.equals(id, water_supply_sourceDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Water_supply_sourceDTO{" +
            "id=" + id +
            ", source_type='" + source_type + "'" +
            '}';
    }
}
