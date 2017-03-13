package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Emission_fuel_type entity.
 */
public class Emission_fuel_typeDTO implements Serializable {

    private UUID id;

    private String fuel_type;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Emission_fuel_typeDTO emission_fuel_typeDTO = (Emission_fuel_typeDTO) o;

        if ( ! Objects.equals(id, emission_fuel_typeDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Emission_fuel_typeDTO{" +
            "id=" + id +
            ", fuel_type='" + fuel_type + "'" +
            '}';
    }
}
