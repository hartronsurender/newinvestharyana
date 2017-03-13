package com.hartron.investharyana.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projectphase entity.
 */
public class ProjectphaseDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private String fciphase;

    private String fci_product_category;

    private String fci;

    private ZonedDateTime fic_implementation_date;

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
    public String getFciphase() {
        return fciphase;
    }

    public void setFciphase(String fciphase) {
        this.fciphase = fciphase;
    }
    public String getFci_product_category() {
        return fci_product_category;
    }

    public void setFci_product_category(String fci_product_category) {
        this.fci_product_category = fci_product_category;
    }
    public String getFci() {
        return fci;
    }

    public void setFci(String fci) {
        this.fci = fci;
    }
    public ZonedDateTime getFic_implementation_date() {
        return fic_implementation_date;
    }

    public void setFic_implementation_date(ZonedDateTime fic_implementation_date) {
        this.fic_implementation_date = fic_implementation_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectphaseDTO projectphaseDTO = (ProjectphaseDTO) o;

        if ( ! Objects.equals(id, projectphaseDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectphaseDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", fciphase='" + fciphase + "'" +
            ", fci_product_category='" + fci_product_category + "'" +
            ", fci='" + fci + "'" +
            ", fic_implementation_date='" + fic_implementation_date + "'" +
            '}';
    }
}
