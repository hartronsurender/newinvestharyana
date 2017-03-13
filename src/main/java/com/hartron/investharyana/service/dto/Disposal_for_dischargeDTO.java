package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Disposal_for_discharge entity.
 */
public class Disposal_for_dischargeDTO implements Serializable {

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

        Disposal_for_dischargeDTO disposal_for_dischargeDTO = (Disposal_for_dischargeDTO) o;

        if ( ! Objects.equals(id, disposal_for_dischargeDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Disposal_for_dischargeDTO{" +
            "id=" + id +
            ", disposal_for_discharge_type='" + disposal_for_discharge_type + "'" +
            '}';
    }
}
