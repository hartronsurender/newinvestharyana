package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Waste_water_nature entity.
 */
public class Waste_water_natureDTO implements Serializable {

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

        Waste_water_natureDTO waste_water_natureDTO = (Waste_water_natureDTO) o;

        if ( ! Objects.equals(id, waste_water_natureDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Waste_water_natureDTO{" +
            "id=" + id +
            ", waste_water_nature_type='" + waste_water_nature_type + "'" +
            '}';
    }
}
