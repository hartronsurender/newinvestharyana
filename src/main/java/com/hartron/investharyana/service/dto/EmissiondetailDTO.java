package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Emissiondetail entity.
 */
public class EmissiondetailDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private UUID particulars;

    private String capacity;

    private UUID type_of_fuel;

    private UUID air_pollution_control_device;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getProjectid() {
        return projectid;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }
    public UUID getParticulars() {
        return particulars;
    }

    public void setParticulars(UUID particulars) {
        this.particulars = particulars;
    }
    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
    public UUID getType_of_fuel() {
        return type_of_fuel;
    }

    public void setType_of_fuel(UUID type_of_fuel) {
        this.type_of_fuel = type_of_fuel;
    }
    public UUID getAir_pollution_control_device() {
        return air_pollution_control_device;
    }

    public void setAir_pollution_control_device(UUID air_pollution_control_device) {
        this.air_pollution_control_device = air_pollution_control_device;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmissiondetailDTO emissiondetailDTO = (EmissiondetailDTO) o;

        if ( ! Objects.equals(id, emissiondetailDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EmissiondetailDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", particulars='" + particulars + "'" +
            ", capacity='" + capacity + "'" +
            ", type_of_fuel='" + type_of_fuel + "'" +
            ", air_pollution_control_device='" + air_pollution_control_device + "'" +
            '}';
    }
}
