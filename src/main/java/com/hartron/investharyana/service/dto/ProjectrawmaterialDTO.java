package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projectrawmaterial entity.
 */
public class ProjectrawmaterialDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private String raw_material;

    private Integer raw_quantity;

    private UUID raw_unit;

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
    public String getRaw_material() {
        return raw_material;
    }

    public void setRaw_material(String raw_material) {
        this.raw_material = raw_material;
    }
    public Integer getRaw_quantity() {
        return raw_quantity;
    }

    public void setRaw_quantity(Integer raw_quantity) {
        this.raw_quantity = raw_quantity;
    }
    public UUID getRaw_unit() {
        return raw_unit;
    }

    public void setRaw_unit(UUID raw_unit) {
        this.raw_unit = raw_unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectrawmaterialDTO projectrawmaterialDTO = (ProjectrawmaterialDTO) o;

        if ( ! Objects.equals(id, projectrawmaterialDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectrawmaterialDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", raw_material='" + raw_material + "'" +
            ", raw_quantity='" + raw_quantity + "'" +
            ", raw_unit='" + raw_unit + "'" +
            '}';
    }
}
