package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Emission_particulars entity.
 */
public class Emission_particularsDTO implements Serializable {

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

        Emission_particularsDTO emission_particularsDTO = (Emission_particularsDTO) o;

        if ( ! Objects.equals(id, emission_particularsDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Emission_particularsDTO{" +
            "id=" + id +
            ", particular_type='" + particular_type + "'" +
            '}';
    }
}
