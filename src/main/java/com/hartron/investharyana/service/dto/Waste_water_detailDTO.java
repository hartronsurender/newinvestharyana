package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Waste_water_detail entity.
 */
public class Waste_water_detailDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private String source_of_generation;

    private UUID nature_type;

    private String quantity;

    private UUID mode_of_disposal;

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
    public String getSource_of_generation() {
        return source_of_generation;
    }

    public void setSource_of_generation(String source_of_generation) {
        this.source_of_generation = source_of_generation;
    }
    public UUID getNature_type() {
        return nature_type;
    }

    public void setNature_type(UUID nature_type) {
        this.nature_type = nature_type;
    }
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public UUID getMode_of_disposal() {
        return mode_of_disposal;
    }

    public void setMode_of_disposal(UUID mode_of_disposal) {
        this.mode_of_disposal = mode_of_disposal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Waste_water_detailDTO waste_water_detailDTO = (Waste_water_detailDTO) o;

        if ( ! Objects.equals(id, waste_water_detailDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Waste_water_detailDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", source_of_generation='" + source_of_generation + "'" +
            ", nature_type='" + nature_type + "'" +
            ", quantity='" + quantity + "'" +
            ", mode_of_disposal='" + mode_of_disposal + "'" +
            '}';
    }
}
