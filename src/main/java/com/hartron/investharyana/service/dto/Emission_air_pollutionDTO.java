package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Emission_air_pollution entity.
 */
public class Emission_air_pollutionDTO implements Serializable {

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

        Emission_air_pollutionDTO emission_air_pollutionDTO = (Emission_air_pollutionDTO) o;

        if ( ! Objects.equals(id, emission_air_pollutionDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Emission_air_pollutionDTO{" +
            "id=" + id +
            ", pollution_controll_device='" + pollution_controll_device + "'" +
            '}';
    }
}
