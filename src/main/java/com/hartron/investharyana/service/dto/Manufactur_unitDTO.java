package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Manufactur_unit entity.
 */
public class Manufactur_unitDTO implements Serializable {

    private UUID id;

    private String unit_type;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getUnit_type() {
        return unit_type;
    }

    public void setUnit_type(String unit_type) {
        this.unit_type = unit_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Manufactur_unitDTO manufactur_unitDTO = (Manufactur_unitDTO) o;

        if ( ! Objects.equals(id, manufactur_unitDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Manufactur_unitDTO{" +
            "id=" + id +
            ", unit_type='" + unit_type + "'" +
            '}';
    }
}
