package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projecttype entity.
 */
public class ProjecttypeDTO implements Serializable {

    private UUID id;

    private String projecttype_name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getProjecttype_name() {
        return projecttype_name;
    }

    public void setProjecttype_name(String projecttype_name) {
        this.projecttype_name = projecttype_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjecttypeDTO projecttypeDTO = (ProjecttypeDTO) o;

        if ( ! Objects.equals(id, projecttypeDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjecttypeDTO{" +
            "id=" + id +
            ", projecttype_name='" + projecttype_name + "'" +
            '}';
    }
}
