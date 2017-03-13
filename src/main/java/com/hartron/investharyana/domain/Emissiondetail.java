package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Emissiondetail.
 */

@Table(name = "emissiondetail")
public class Emissiondetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
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

    public Emissiondetail projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public UUID getParticulars() {
        return particulars;
    }

    public Emissiondetail particulars(UUID particulars) {
        this.particulars = particulars;
        return this;
    }

    public void setParticulars(UUID particulars) {
        this.particulars = particulars;
    }

    public String getCapacity() {
        return capacity;
    }

    public Emissiondetail capacity(String capacity) {
        this.capacity = capacity;
        return this;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public UUID getType_of_fuel() {
        return type_of_fuel;
    }

    public Emissiondetail type_of_fuel(UUID type_of_fuel) {
        this.type_of_fuel = type_of_fuel;
        return this;
    }

    public void setType_of_fuel(UUID type_of_fuel) {
        this.type_of_fuel = type_of_fuel;
    }

    public UUID getAir_pollution_control_device() {
        return air_pollution_control_device;
    }

    public Emissiondetail air_pollution_control_device(UUID air_pollution_control_device) {
        this.air_pollution_control_device = air_pollution_control_device;
        return this;
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
        Emissiondetail emissiondetail = (Emissiondetail) o;
        if (emissiondetail.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, emissiondetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Emissiondetail{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", particulars='" + particulars + "'" +
            ", capacity='" + capacity + "'" +
            ", type_of_fuel='" + type_of_fuel + "'" +
            ", air_pollution_control_device='" + air_pollution_control_device + "'" +
            '}';
    }
}
