package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Waste_water_disposal_mode entity.
 */
public class Waste_water_disposal_modeDTO implements Serializable {

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

        Waste_water_disposal_modeDTO waste_water_disposal_modeDTO = (Waste_water_disposal_modeDTO) o;

        if ( ! Objects.equals(id, waste_water_disposal_modeDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Waste_water_disposal_modeDTO{" +
            "id=" + id +
            ", disposal_mode='" + disposal_mode + "'" +
            '}';
    }
}
